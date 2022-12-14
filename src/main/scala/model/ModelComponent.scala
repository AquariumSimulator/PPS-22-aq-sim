package model

import model.aquarium._
import model.chronicle.{Chronicle, Events}
import model.db.PrologEngine
import model.fish.{Fish, UpdateFish}
import model.food.{Food, UpdateFood}
import model.interaction.Interaction
import model.interaction.MultiplierVelocityFish.{SPEED_MULTIPLIER_IMPURITY, SPEED_MULTIPLIER_TEMPERATURE}

import java.util.concurrent.ConcurrentLinkedQueue
import scala.annotation.tailrec
import scala.reflect.ClassTag

/** Model methods implementation from [[Model]]. */
trait ModelComponent:
  class ModelImpl extends Model:

    private val queue: ConcurrentLinkedQueue[Aquarium => Aquarium] = new ConcurrentLinkedQueue()

    private var currentChronicle: Chronicle = Chronicle()

    override def chronicle: Chronicle = currentChronicle

    override def addChronicleEvent(event: String): Unit =
      currentChronicle = currentChronicle.addEvent(event)

    override def getDatabase: PrologEngine = PrologEngine

    override def addUserInteraction(interaction: Aquarium => Aquarium): Unit =
      queue.add(interaction)

    override def initializeAquarium(
        herbivorousFishNumber: Int,
        carnivorousFishNumber: Int,
        algaeNumber: Int
    ): Aquarium =
      Aquarium(herbivorousFishNumber, carnivorousFishNumber, algaeNumber)

    override def step(currentAquarium: Aquarium): Aquarium =

      val multiplier = (aqState: AquariumState) =>
        SPEED_MULTIPLIER_TEMPERATURE(aqState.temperature) *
          SPEED_MULTIPLIER_IMPURITY(aqState.impurity)

      val aquarium = queue.isEmpty match
        case true => currentAquarium
        case _ => Iterator.iterate(currentAquarium, queue.size() + 1)(queue.poll()).toList.last

      val updatedAquariumState: AquariumState = newAquariumState(
        aquarium.population.fish
          .concat(aquarium.population.algae)
          .concat(aquarium.availableFood),
        aquarium.aquariumState
      )((s: AquariumState, e: Entity) => Interaction(s, e).update())

      val (fish: Set[Fish], newFood: Set[Food]) =
        entityEntityInteractions(aquarium.population.fish, aquarium.availableFood)((fish: Fish, food: Food) =>
          fish.collidesWith(food) && fish.feedingType == food.feedingType
        )((fish: Fish, food: Food) => Interaction(fish, food).update())

      val (newHerbivorous: Set[Fish], algae: Set[Algae]) =
        entityEntityInteractions(fish.filter(f => f.feedingType == FeedingType.HERBIVOROUS), aquarium.population.algae)(
          (fish: Fish, algae: Algae) => fish.collidesWith(algae)
        )((fish: Fish, algae: Algae) => Interaction(fish, algae).update())

      val newFish =
        entityStep(
          fishFishInteractions(fish.filter(f => f.feedingType == FeedingType.CARNIVOROUS).concat(newHerbivorous)),
          updatedAquariumState
        )((f: Fish) => f.isAlive)((f: Fish, a: AquariumState) =>
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

      val newPopulation: Population = Population(
        newFish,
        entityStep(algae, updatedAquariumState)((a: Algae) => a.height > 0)((al: Algae, a: AquariumState) =>
          Interaction(al, a)
            .update()
        )
      )

      Aquarium(
        updatedAquariumState,
        newPopulation,
        for food <- newFood
        yield UpdateFood(food).move(Food.SPEED_MULTIPLIER)
      )

    private def newAquariumState[A](entities: Set[A], initialState: AquariumState)(
        action: (AquariumState, A) => AquariumState
    ): AquariumState =
      @tailrec
      def _newAquariumState[A](population: Set[A], aquariumState: AquariumState)(
          action: (AquariumState, A) => AquariumState
      ): AquariumState =
        population match
          case p if p.nonEmpty => _newAquariumState(p.tail, action(aquariumState, p.head))(action)
          case _ => aquariumState

      _newAquariumState(entities, initialState)(action)

    private def entityStep[A](entities: Set[A], aquariumState: AquariumState)(
        isAlive: A => Boolean
    )(action: (A, AquariumState) => Option[A]): Set[A] =
      for
        elem <- entities
        if isAlive(elem)
        newElem <- action(elem, aquariumState)
      yield newElem

    private def entityEntityInteractions[A: ClassTag, B, C](setA: Set[A], setB: Set[B])(
        tuplesCondition: (A, B) => Boolean
    )(action: (A, B) => C): (Set[A], Set[B]) =
      val tuples = for
        s1 <- setA
        s2 <- setB
        if tuplesCondition(s1, s2)
      yield (s1, s2)

      def update(tuple: (A, B), newA: A)(tuples: Set[(A, B)], newSetA: Set[A]): (Set[(A, B)], Set[A]) =
        (
          tuples
            .filterNot(t => t == tuple)
            .map(t =>
              t match
                case t if t._1 == tuple._1 => (newA, t._2)
                case _ => t
            ),
          newSetA.filterNot(f => f == tuple._1) + newA
        )

      @tailrec
      def _entityEntityInteractions(tuples: Set[(A, B)], setA: Set[A], setB: Set[B]): (Set[A], Set[B]) =
        tuples.nonEmpty match
          case true =>
            val tuple = tuples.head
            val (newTuples: Set[(A, B)], newSetA: Set[A], newSetB: Set[B]) = action(tuple._1, tuple._2) match
              case (a: A, Some(_)) =>
                val (updatedTuples: Set[(A, B)], updatedSetA: Set[A]) = update(tuple, a)(tuples, setA)
                (updatedTuples, updatedSetA, setB)
              case (a: A, None) =>
                val (updatedTuples: Set[(A, B)], updatedSetA: Set[A]) =
                  update(tuple, a)(tuples.filterNot(t => t._2 == tuple._2), setA)
                (updatedTuples, updatedSetA, setB - tuple._2)
            _entityEntityInteractions(newTuples, newSetA, newSetB)
          case _ => (setA, setB)

      _entityEntityInteractions(tuples, setA, setB)

    private def fishFishInteractions(fish: Set[Fish]): Set[Fish] =
      val tuples = fish.toList.tails
        .filter(_.nonEmpty)
        .flatMap(f => f.tail.map((f.head, _)))
        .filter(tuple => tuple._1.collidesWith(tuple._2))
        .toList

      def checkAndUpdate(
          oldFish: Fish,
          newFish: Option[Fish]
      )(tuples: List[(Fish, Fish)], fish: Set[Fish]): (List[(Fish, Fish)], Set[Fish]) =
        if newFish.isEmpty then (tuples.filter(t => t._1 != oldFish && t._2 != oldFish), fish)
        else
          (
            tuples
              .map(t =>
                t match
                  case t if t._1 == oldFish => (newFish.get, t._2)
                  case t if t._2 == oldFish => (t._1, newFish.get)
                  case _ => t
              ),
            fish + newFish.get
          )

      @tailrec
      def _fishFishInteractions(tuples: List[(Fish, Fish)], fishFishInteraction: Set[Fish]): Set[Fish] =
        tuples.nonEmpty match
          case true =>
            val tuple = tuples.head
            val res = Interaction(tuple._1, tuple._2).update()

            val newFishFishInteraction = fishFishInteraction.filterNot(f => f == tuple._1).filterNot(f => f == tuple._2)

            val (newTuples: List[(Fish, Fish)], newFish: Set[Fish]) = checkAndUpdate(tuple._2, res._2)(
              checkAndUpdate(tuple._1, res._1)(tuples, newFishFishInteraction)._1,
              checkAndUpdate(tuple._1, res._1)(tuples, newFishFishInteraction)._2
            )

            res._3.isDefined && newFish.size < AquariumParametersLimits.FISH_MAX match
              case true => _fishFishInteractions(newTuples.filterNot(t => t == tuple), newFish + res._3.get)
              case _ => _fishFishInteractions(newTuples.filterNot(t => t == tuple), newFish)
          case _ => fishFishInteraction

      _fishFishInteractions(tuples, fish)

    override def saveAquarium(aquarium: Aquarium, iteration: Int): Unit =
      aquarium.population.fish.foreach(fish => PrologEngine.saveFish(fish, iteration))
      aquarium.population.algae.foreach(algae => PrologEngine.saveAlgae(algae, iteration))
