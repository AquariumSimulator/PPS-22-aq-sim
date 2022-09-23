package model

import model.aquarium.*
import model.fish.{Fish, UpdateFish}
import model.food.Food
import model.interaction.Interaction
import model.interaction.MultiplierVelocityFish.{SPEED_MULTIPLIER_IMPURITY, SPEED_MULTIPLIER_TEMPERATURE}
import mvc.MVC.model.*

import scala.annotation.tailrec

/** Model methods implementation from [[Model]]. */
trait ModelImpl:
  class ModelImpl extends Model:
    override def initializeAquarium(
        herbivorousFishNumber: Int,
        carnivorousFishNumber: Int,
        algaeNumber: Int
    ): Aquarium =
      Aquarium(herbivorousFishNumber, carnivorousFishNumber, algaeNumber)

    override def updateTemperatureByUser(temperature: Double, aquarium: Aquarium): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.updateTemperature(temperature))

    override def updateBrightnessByUser(brightness: Double, aquarium: Aquarium): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.updateBrightness(brightness))

    override def cleanByUser(aquarium: Aquarium): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.updateImpurity(0))

    override def updateOxygenationByUser(oxygenation: Double, aquarium: Aquarium): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.updateOxygenation(oxygenation))

    override def addInhabitant[A](aquarium: Aquarium, inhabitant: A): Aquarium =
      aquarium.copy(population = aquarium.population.addInhabitant(inhabitant))

    override def removeInhabitant[A](aquarium: Aquarium, inhabitant: A): Aquarium =
      aquarium.copy(population = aquarium.population.removeInhabitant(inhabitant))

    override def addFood(aquarium: Aquarium, food: Food): Aquarium =
      aquarium.copy(availableFood = aquarium.availableFood.addFood(food))

    override def removeFood(aquarium: Aquarium, food: Food): Aquarium =
      aquarium.copy(availableFood = aquarium.availableFood.deleteFood(food))

    override def step(aquarium: Aquarium): Aquarium =
      val newState =
        newAquariumState(
          aquarium.population.herbivorous.concat(aquarium.population.carnivorous),
          newAquariumState(aquarium.population.algae, aquarium.aquariumState)((s: AquariumState, a: Algae) =>
            Interaction(s, a).update()
          )
        )((s: AquariumState, f: Fish) => Interaction(s, f).update())

      val isFoodNear = (food: Food, fish: Fish) => food.position == fish.position
      val carnivorous =
        foodInteraction(aquarium.population.carnivorous, aquarium.availableFood.carnivorousFood)(isFoodNear)
      val herbivorous =
        foodInteraction(aquarium.population.herbivorous, aquarium.availableFood.herbivorousFood)(isFoodNear)

      val fishAlgaeInteraction =
        calculateInteractionAlgae(herbivorous._1, aquarium.population.algae)((f: Fish, a: Algae) =>
          f.position._1 == a.base
        )
      val fishFishInteraction =
        calculateInteractionFish(carnivorous._1.concat(fishAlgaeInteraction._1))((f1: Fish, f2: Fish) =>
          f1.position == f2.position
        )

      val multiplier =
        SPEED_MULTIPLIER_TEMPERATURE(newState.temperature) *
          SPEED_MULTIPLIER_IMPURITY(newState.impurity)

      val newHerbivorous =
        entityStep(fishFishInteraction.filter(f => f.feedingType == FeedingType.HERBIVOROUS), newState)((f: Fish) =>
          f.isAlive
        )((f: Fish, a: AquariumState) => Interaction(UpdateFish(f).move(multiplier), a).update())
      // TODO shift della fame deve essere una costante
      // TODO aggiorna valore riproduzione
      val newCarnivorous =
        entityStep(fishFishInteraction.filter(f => f.feedingType == FeedingType.CARNIVOROUS), newState)((f: Fish) =>
          f.isAlive
        )((f: Fish, a: AquariumState) =>
          Interaction(UpdateFish(UpdateFish(f).updateHunger(f.hunger - 5)).move(multiplier), a).update()
        )
      val newAlgae =
        entityStep(fishAlgaeInteraction._2, newState)((a: Algae) => a.height > 0)((al: Algae, a: AquariumState) =>
          Interaction(al, a).update()
        )

      val newAvailableFood: AvailableFood = AvailableFood(herbivorous._2, carnivorous._2)
      val newPopulation: Population = Population(newHerbivorous, newCarnivorous, newAlgae)

      Aquarium(newState, newPopulation, newAvailableFood)

    private def foodInteraction(set: Set[Fish], foodSet: Set[Food])(
        isNear: (Food, Fish) => Boolean
    ): (Set[Fish], Set[Food]) =
      if set.isEmpty || foodSet.isEmpty then
        var newFoodSet = foodSet
        var newSet = set
        for
          fish <- set
          food <- foodSet
          if isNear(food, fish)
        do
          newSet = newSet - fish
          newSet = newSet + UpdateFish(fish).eat(food)
          newFoodSet = newFoodSet - food
        (newSet, newFoodSet)
      else (set, foodSet)

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

    def calculateInteractionFish(setFish: Set[Fish])(isNear: (Fish, Fish) => Boolean): Set[Fish] =
      var newSet: Set[Fish] = Set.empty

      @tailrec
      def _calculateInteraction(
          fish: Option[Fish],
          setFish: Set[Fish],
          newSetFish: Set[Fish]
      ): (Option[Fish], Set[Fish]) =
        setFish match
          case s if s.nonEmpty && fish.isDefined =>
            if fish.get != s.head && isNear(fish.get, s.head) then
              val interaction = Interaction(fish.get, s.head).update()
              if interaction._3.isDefined then newSet = newSet + interaction._3.get
              _calculateInteraction(
                interaction._1,
                s.tail,
                if interaction._2.isDefined then newSetFish + interaction._2.get else newSetFish
              )
            else _calculateInteraction(fish, s.tail, newSetFish + s.head)
          case _ => (fish, newSetFish)

      for
        fish <- setFish
        res = _calculateInteraction(Some(fish), setFish, Set.empty)
      do
        newSet = res._2
        if res._1.isDefined then newSet = newSet + res._1.get

      newSet
