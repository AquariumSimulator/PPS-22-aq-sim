package model

import model.aquarium.*
import model.fish.{Fish, UpdateFish}
import model.food.{Food, UpdateFood}
import model.interaction.Interaction
import model.interaction.MultiplierVelocityFish.{SPEED_MULTIPLIER_IMPURITY, SPEED_MULTIPLIER_TEMPERATURE}
import mvc.MVC.model.*

import java.util.concurrent.ConcurrentLinkedQueue
import scala.annotation.tailrec
import scala.language.postfixOps

/** Model methods implementation from [[Model]]. */
trait ModelImpl:
  class ModelImpl extends Model:

    private val queue: ConcurrentLinkedQueue[Aquarium => Aquarium] = new ConcurrentLinkedQueue()

    private val multiplier = (aqState: AquariumState) =>
      SPEED_MULTIPLIER_TEMPERATURE(aqState.temperature) *
        SPEED_MULTIPLIER_IMPURITY(aqState.impurity)
    override def addUserInteraction(interaction: Aquarium => Aquarium): Unit =
      queue.add(interaction)
    override def initializeAquarium(
        herbivorousFishNumber: Int,
        carnivorousFishNumber: Int,
        algaeNumber: Int
    ): Aquarium =
      Aquarium(herbivorousFishNumber, carnivorousFishNumber, algaeNumber)
    override def step(aquarium: Aquarium): Aquarium =

      val updatedAquariumState: AquariumState = updateAquariumState(aquarium)

      val updatedCarnivorous =
        foodInteraction(aquarium.population.carnivorous, aquarium.availableFood.carnivorousFood)
      val updateHerbivorous =
        foodInteraction(aquarium.population.herbivorous, aquarium.availableFood.herbivorousFood)

      val fishAlgaeInteraction =
        fishAlgaeInteractions(updateHerbivorous._1, aquarium.population.algae)

      val fishFishInteraction = fishFishInteractions(updatedCarnivorous._1.concat(fishAlgaeInteraction._1))

      val newHerbivorous =
        entityStep(fishFishInteraction.filter(f => f.feedingType == FeedingType.HERBIVOROUS), updatedAquariumState)(
          (f: Fish) => f.isAlive
        )((f: Fish, a: AquariumState) =>
          Interaction(
            UpdateFish(
              UpdateFish(
                UpdateFish(f)
                  .updateReproductionFactor(f.reproductionFactor + Fish.REPRODUCTION_FACTOR_SHIFT)
              )
                .updateSatiety(f.satiety - Fish.SATIETY_SHIFT)
            )
              .move(multiplier(updatedAquariumState)),
            a
          )
            .update()
        )

      val newCarnivorous =
        entityStep(fishFishInteraction.filter(f => f.feedingType == FeedingType.CARNIVOROUS), updatedAquariumState)(
          (f: Fish) => f.isAlive
        )((f: Fish, a: AquariumState) =>
          Interaction(
            UpdateFish(
              UpdateFish(
                if f.reproductionFactor < Fish.MAX_REPRODUCTION_FACTOR then
                  UpdateFish(f)
                    .updateReproductionFactor(f.reproductionFactor + Fish.REPRODUCTION_FACTOR_SHIFT)
                else f
              )
                .updateSatiety(f.satiety - Fish.SATIETY_SHIFT)
            )
              .move(multiplier(updatedAquariumState)),
            a
          )
            .update()
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

      val newAvailableFood: AvailableFood = AvailableFood(
        newFood.filter(f => f.feedingType == FeedingType.HERBIVOROUS),
        newFood.filter(f => f.feedingType == FeedingType.CARNIVOROUS)
      )
      val newPopulation: Population = Population(newHerbivorous, newCarnivorous, newAlgae)

      val stepAquarium = Aquarium(updatedAquariumState, newPopulation, newAvailableFood)

      queue.isEmpty match
        case true => stepAquarium
        case _ => Iterator.iterate(stepAquarium, queue.size() + 1)(queue.poll()).toList.last

    private def updateAquariumState(aquarium: Aquarium): AquariumState =
      newAquariumState(
        aquarium.population.herbivorous.concat(aquarium.population.carnivorous),
        newAquariumState(aquarium.population.algae, aquarium.aquariumState)((s: AquariumState, a: Algae) =>
          Interaction(s, a).update()
        )
      )((s: AquariumState, f: Fish) => Interaction(s, f).update())

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

    private def foodInteraction(set: Set[Fish], foodSet: Set[Food]) =
      if set.nonEmpty && foodSet.nonEmpty then
        var newFoodSet = foodSet
        var newSet = set
        for
          fish <- set
          food <- foodSet
          if fish.satiety < Fish.MAX_SATIETY - food.nutritionAmount && fish.collidesWith(food)
        do
          println(fish.name + "  ha MANGIATO " + fish.feedingType + " - " + food.feedingType)
          newSet = newSet - fish
          newSet = newSet + UpdateFish(fish).eat(food)
          newFoodSet = newFoodSet - food
        (newSet, newFoodSet)
      else (set, foodSet)

    private def entityStep[A](set: Set[A], aquariumState: AquariumState)(
        isAlive: A => Boolean
    )(action: (A, AquariumState) => Option[A]): Set[A] =
      for
        elem <- set
        if isAlive(elem)
        newElem <- action(elem, aquariumState)
      yield newElem
  private def fishAlgaeInteractions(fish: Set[Fish], algae: Set[Algae]): (Set[Fish], Set[Algae]) =
    var newFish: Set[Fish] = fish
    var newAlgae: Set[Algae] = algae
    var tuples = for
      f <- fish
      a <- algae
      if f.collidesWith(a)
    yield (f, a)

    for
      tuple <- tuples
      res = Interaction(tuple._1, tuple._2).update()
    do
      newFish = newFish.filterNot(f => f.name == tuple._1.name) + res._1
      tuples = tuples
        .map(t =>
          t match
            case t if t._1.name == tuple._1.name => (res._1, t._2)
            case _ => t
        )
      if res._2.isEmpty then
        newAlgae = newAlgae - tuple._2
        tuples = tuples.filterNot(t => t._2 == tuple._2)
    (newFish, newAlgae)

  private def fishFishInteractions(set: Set[Fish]): Set[Fish] =
    var tuples = set.toList.tails
      .filter(_.nonEmpty)
      .flatMap(f => f.tail.map((f.head, _)))
      .filter(tuple => tuple._1.collidesWith(tuple._2))
      .toList
    var fishFishInteraction = set
    def checkAndUpdate(oldFish: String, newFish: Option[Fish]): Unit =
      if newFish.isEmpty then tuples = tuples.filter(t => t._1.name != oldFish && t._2.name != oldFish)
      else
        tuples = tuples
          .map(t =>
            t match
              case t if t._1.name == oldFish => (newFish.get, t._2)
              case t if t._2.name == oldFish => (t._1, newFish.get)
              case _ => t
          )
        fishFishInteraction = fishFishInteraction + newFish.get

    for
      tuple <- tuples
      res = Interaction(tuple._1, tuple._2).update()
    do
      fishFishInteraction = fishFishInteraction.filterNot(f => f.name == tuple._1.name)
      fishFishInteraction = fishFishInteraction.filterNot(f => f.name == tuple._2.name)

      checkAndUpdate(tuple._1.name, res._1)
      checkAndUpdate(tuple._2.name, res._2)

      if res._3.isDefined && fishFishInteraction.size < AquariumParametersLimits.FISH_MAX
      then fishFishInteraction = fishFishInteraction + res._3.get

    fishFishInteraction
