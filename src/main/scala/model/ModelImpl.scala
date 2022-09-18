package model

import model.aquarium.*
import model.fish.{FeedingType, Fish, UpdateFish}
import model.interaction.Interaction
import mvc.MVC.model.*

/** Model methods implementation from [[Model]]. */
trait ModelImpl:
  class ModelImpl extends Model:

    def m() = 1
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

    override def addFood[A](aquarium: Aquarium, food: A): Aquarium =
      aquarium.copy(availableFood = aquarium.availableFood.addFood(food))

    override def removeFood[A](aquarium: Aquarium, food: A): Aquarium =
      aquarium.copy(availableFood = aquarium.availableFood.deleteFood(food))

    override def step(aquarium: Aquarium): Aquarium =
      // TODO refactoring
      var newState = aquarium.aquariumState
      aquarium.population.algae.foreach(a => newState = Interaction(newState, a).update())
      aquarium.population.herbivorous.foreach(h => newState = Interaction(newState, h).update())
      aquarium.population.carnivorous.foreach(c => newState = Interaction(newState, c).update())

      val newPopulation = Population(
        genericFishStep(aquarium.population.herbivorous, newState),
        genericFishStep(aquarium.population.carnivorous, newState),
        algaeStep(aquarium.population.algae, newState)
      )

      // TODO INTERAZIONI PESCI

      aquarium.copy(aquariumState = newState, population = newPopulation)

    private def genericFishStep(set: Set[Fish], aquariumState: AquariumState): Set[Fish] =
      // TODO interazione pesce <--> alghe
      // TODO interazione pesce <--> pesci

      set
        .filter(fish => fish.isAlive)
        .map(fish => Interaction(UpdateFish(fish).move(), aquariumState).update())
        .filter(fish => fish.isDefined)
        .map(fish => fish.get)

    private def algaeStep(set: Set[Algae], aquariumState: AquariumState): Set[Algae] =
      set
        .map(algae => Interaction(algae, aquariumState).update())
        .filter(algae => algae.isDefined)
        .map(algae => algae.get)
