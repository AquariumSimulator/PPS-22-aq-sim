package model

import model.aquarium.*
import scala.reflect.ClassTag
import model.chronicle.{Chronicle, Events}
import model.db.PrologEngine
import model.fish.{Fish, UpdateFish}
import model.food.{Food, UpdateFood}
import model.interaction.Interaction
import model.interaction.MultiplierVelocityFish.{SPEED_MULTIPLIER_IMPURITY, SPEED_MULTIPLIER_TEMPERATURE}
import mvc.MVC.model

import java.util.concurrent.ConcurrentLinkedQueue
import scala.annotation.tailrec

/** Model methods implementation from [[Model]]. */
trait ModelImpl:
  class ModelImpl extends Model:

    private var currentChronicle: Chronicle = Chronicle()

    override def chronicle: Chronicle = currentChronicle

    override def addChronicleEvent(event: String): Unit =
      currentChronicle = currentChronicle.addEvent(event)

    private val queue: ConcurrentLinkedQueue[Aquarium => Aquarium] = new ConcurrentLinkedQueue()
    private val multiplier = (aqState: AquariumState) =>
      SPEED_MULTIPLIER_TEMPERATURE(aqState.temperature) *
        SPEED_MULTIPLIER_IMPURITY(aqState.impurity)

    override def getDatabase: PrologEngine = PrologEngine

    override def addUserInteraction(interaction: Aquarium => Aquarium): Unit =
      queue.add(interaction)

    override def initializeAquarium(
        herbivorousFishNumber: Int,
        carnivorousFishNumber: Int,
        algaeNumber: Int
    ): Aquarium =
      Aquarium(herbivorousFishNumber, carnivorousFishNumber, algaeNumber)

    private val foodCondition = (fish: Fish, food: Food) =>
      fish.satiety < (Fish.MAX_SATIETY - food.nutritionAmount) && fish.collidesWith(food)
    private val foodAction = (fish: Fish, food: Food) => fish.eat(food)

    override def step(currentAquarium: Aquarium): Aquarium =

      val aquarium = queue.isEmpty match
        case true => currentAquarium
        case _ => Iterator.iterate(currentAquarium, queue.size() + 1)(queue.poll()).toList.last

      val updatedAquariumState: AquariumState = newAquariumState(
        aquarium.population.fish
          .concat(aquarium.population.algae)
          .concat(aquarium.availableFood),
        aquarium.aquariumState
      )((s: AquariumState, e: Entity) => Interaction(s, e).update())

      val updatedCarnivorous =
        EntityEntityInteractions(aquarium.population.carnivorous, aquarium.carnivorousFood)(foodCondition)(foodAction)
      val updateHerbivorous =
        EntityEntityInteractions(aquarium.population.herbivorous, aquarium.herbivorousFood)(foodCondition)(foodAction)

      val fishAlgaeInteraction =
        EntityEntityInteractions(updateHerbivorous._1, aquarium.population.algae)((fish: Fish, algae: Algae) =>
          fish.collidesWith(algae)
        )((fish: Fish, algae: Algae) => Interaction(fish, algae).update())

      val fishFishInteraction =
        fishFishInteractions(updatedCarnivorous._1.concat(fishAlgaeInteraction._1))

      val newFish =
        entityStep(fishFishInteraction, updatedAquariumState)((f: Fish) => f.isAlive)((f: Fish, a: AquariumState) =>
          val fish: Fish =
            if f.reproductionFactor < Fish.MAX_REPRODUCTION_FACTOR
            then f.updateReproductionFactor(f.reproductionFactor + Fish.REPRODUCTION_FACTOR_SHIFT)
            else f

          Interaction(
            fish
              .copy(age = fish.age + Fish.AGE_SHIFT)
              .updateSatiety(f.satiety - Fish.SATIETY_SHIFT)
              .move(multiplier(updatedAquariumState)),
            a
          ).update()
        )

      val newAlgae =
        entityStep(fishAlgaeInteraction._2, updatedAquariumState)((a: Algae) => a.height > 0)(
          (al: Algae, a: AquariumState) =>
            Interaction(al, a)
              .update()
        )

      val newFood =
        for food <- updateHerbivorous._2.concat(updatedCarnivorous._2)
        yield UpdateFood(food).move(1)

      val newPopulation: Population = Population(newFish, newAlgae)

      Aquarium(updatedAquariumState, newPopulation, newFood)

    private def newAquariumState[A](population: Set[A], initialState: AquariumState)(
        func: (AquariumState, A) => AquariumState
    ): AquariumState =
      @tailrec
      def _newAquariumState[A](population: Set[A], aquariumState: AquariumState)(
          func: (AquariumState, A) => AquariumState
      ): AquariumState =
        population match
          case p if p.nonEmpty => _newAquariumState(p.tail, func(aquariumState, p.head))(func)
          case _ => aquariumState

      _newAquariumState(population, initialState)(func)

    private def entityStep[A](set: Set[A], aquariumState: AquariumState)(
        isAlive: A => Boolean
    )(action: (A, AquariumState) => Option[A]): Set[A] =
      for
        elem <- set
        if isAlive(elem)
        newElem <- action(elem, aquariumState)
      yield newElem

    private def EntityEntityInteractions[A: ClassTag, B, C](set1: Set[A], set2: Set[B])(
        tuplesCondition: (A, B) => Boolean
    )(action: (A, B) => C): (Set[A], Set[B]) =
      var newSet1: Set[A] = set1
      var newSet2: Set[B] = set2
      var tuples = for
        s1 <- set1
        s2 <- set2
        if tuplesCondition(s1, s2)
      yield (s1, s2)

      def update(tuple: (A, B), newA: A): Unit =
        newSet1 = newSet1.filterNot(f => f == tuple._1) + newA
        tuples = tuples
          .filterNot(t => t == tuple)
          .map(t =>
            t match
              case t if t._1 == tuple._1 => (newA, t._2)
              case _ => t
          )
      for
        tuple <- tuples
        res = action(tuple._1, tuple._2)
      do
        res match
          case a: A =>
            newSet2 = newSet2 - tuple._2
            update(tuple, a)
          case (a: A, Some(_)) =>
            update(tuple, a)
          case (a: A, None) =>
            update(tuple, a)
            newSet2 = newSet2 - tuple._2
            tuples = tuples.filterNot(t => t._2 == tuple._2)

      (newSet1, newSet2)
    private def fishFishInteractions(set: Set[Fish]): Set[Fish] =
      var tuples = set.toList.tails
        .filter(_.nonEmpty)
        .flatMap(f => f.tail.map((f.head, _)))
        .filter(tuple => tuple._1.collidesWith(tuple._2))
        .toList
      var fishFishInteraction = set
      def checkAndUpdate(oldFish: Fish, newFish: Option[Fish]): Unit =
        if newFish.isEmpty then tuples = tuples.filter(t => t._1 != oldFish && t._2 != oldFish)
        else
          tuples = tuples
            .map(t =>
              t match
                case t if t._1 == oldFish => (newFish.get, t._2)
                case t if t._2 == oldFish => (t._1, newFish.get)
                case _ => t
            )
          fishFishInteraction = fishFishInteraction + newFish.get

      for
        tuple <- tuples
        res = Interaction(tuple._1, tuple._2).update()
      do
        fishFishInteraction = fishFishInteraction.filterNot(f => f == tuple._1)
        fishFishInteraction = fishFishInteraction.filterNot(f => f == tuple._2)

        checkAndUpdate(tuple._1, res._1)
        checkAndUpdate(tuple._2, res._2)
        tuples = tuples.filterNot(t => t == tuple)

        if res._3.isDefined && fishFishInteraction.size < AquariumParametersLimits.FISH_MAX
        then fishFishInteraction = fishFishInteraction + res._3.get

      fishFishInteraction

    override def saveAquarium(aquarium: Aquarium, iteration: Int): Unit =
      aquarium.population.fish.foreach(fish => PrologEngine.saveFish(fish, iteration))
      aquarium.population.algae.foreach(algae => PrologEngine.saveAlgae(algae, iteration))
