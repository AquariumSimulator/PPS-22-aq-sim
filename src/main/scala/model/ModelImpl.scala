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

    private val isFoodNear = (food: Food, fish: Fish) => food.position == fish.position
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
      println("-> " + aquarium.population.herbivorous.concat(aquarium.population.carnivorous).size)

      val updatedAquariumState: AquariumState = updateAquariumState(aquarium)

      val updatedCarnivorous =
        foodInteraction(aquarium.population.carnivorous, aquarium.availableFood.carnivorousFood)
      val updateHerbivorous =
        foodInteraction(aquarium.population.herbivorous, aquarium.availableFood.herbivorousFood)

      val fishAlgaeInteraction =
        calculateInteractionAlgae(updateHerbivorous._1, aquarium.population.algae)((f: Fish, a: Algae) =>
          f.position._1 == a.base
        )

      var fishFishInteraction = updatedCarnivorous._1.concat(fishAlgaeInteraction._1)
      println("-> " + fishFishInteraction.size)

      updatedCarnivorous._1.concat(fishAlgaeInteraction._1).toList.tails
        .filter(_.nonEmpty)
        .flatMap(f => f.tail.map((f.head, _)))
        .filter(tuple => tuple._1.collidesWith(tuple._2)).toList
        .foreach(tuple =>
          //TODO refactoring
          val newFish = Interaction(tuple._1, tuple._2).update()
          if newFish._1.isEmpty
          then
            fishFishInteraction = fishFishInteraction - tuple._1
          else
            fishFishInteraction = fishFishInteraction - tuple._1 + newFish._1.get
          if newFish._2.isEmpty
          then fishFishInteraction = fishFishInteraction - tuple._2
          else
            fishFishInteraction = fishFishInteraction - tuple._2 + newFish._2.get
          if newFish._3.isDefined && fishFishInteraction.size < AquariumParametersLimits.FISH_MAX
          then
            fishFishInteraction = fishFishInteraction + newFish._3.get
        )
      println("--> " + fishFishInteraction.size)

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
                .updateHunger(f.hunger - Fish.HUNGER_SHIFT)
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
                .updateHunger(f.hunger - Fish.HUNGER_SHIFT)
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
      println("________")

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
          if fish.hunger < Fish.MAX_HUNGER - food.nutritionAmount && fish.collidesWith(food)
        do
          println(fish.name + "  ha MANGIATO -> "+ fish.feedingType + " - " + food.feedingType)
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

    def calculateInteractionAlgae(setFish: Set[Fish], setAlgae: Set[Algae])(
        isNear: (Fish, Algae) => Boolean
    ): (Set[Fish], Set[Algae]) =
      var newFish: Set[Fish] = Set.empty
      var newAlgae = setAlgae
      @tailrec
      def _calculateInteraction(fish: Fish, setAlgae: Set[Algae], newSetAlgae: Set[Algae]): (Fish, Set[Algae]) =
        setAlgae match
          case s if s.nonEmpty =>
            if isNear(fish, s.head) then
              val interaction = Interaction(fish, s.head).update()
              _calculateInteraction(
                interaction._1,
                s.tail,
                if interaction._2.isDefined then newSetAlgae + interaction._2.get else newSetAlgae
              )
            else _calculateInteraction(fish, s.tail, newSetAlgae + s.head)
          case _ => (fish, newSetAlgae)

      for
        fish <- setFish
        res = _calculateInteraction(fish, newAlgae, Set.empty)
      do
        newFish = newFish + res._1
        newAlgae = res._2

      (newFish, newAlgae)
