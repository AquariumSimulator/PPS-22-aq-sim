package controller

import model.aquarium.{Aquarium, InitializeAquarium}
import model.food.Food
import mvc.ControllerModule.ControllerRequirements
import mvc.MVC.given_ControllerRequirements as context
import java.util.stream.IntStream

/** Controller methods implementation from [[Controller]]. */
trait ControllerImpl:
  context: ControllerRequirements =>
  given ControllerRequirements = context
  class ControllerImpl extends Controller:

    val simEngine: SimulationEngine = SimulationEngine(
      context.model.initializeAquarium(
        InitializeAquarium.HERBIVOROUS_FISH,
        InitializeAquarium.CARNIVOROUS_FISH,
        InitializeAquarium.ALGAE
      )
    )
    override def startSimulation(simSpeed: SimulationSpeed): Unit =
      simEngine.start(simSpeed)

    override def stopSimulation(): Unit =
      simEngine.stop()

    override def changeSpeed(simSpeed: SimulationSpeed): Unit =
      simEngine.changeSpeed(simSpeed)

    override def getSpeed(): SimulationSpeed =
      simEngine.getSpeed()

    override def isRunning(): Boolean =
      simEngine.isRunning()

    override def getAquarium(): Aquarium =
      simEngine.getAquarium()

    override def updateTemperature(temperature: Double): Unit =
      context.model.addUserInteraction((aq: Aquarium) =>
        aq.copy(aquariumState = aq.aquariumState.updateTemperature(temperature))
      )

    override def updateBrightness(brightness: Double): Unit =
      context.model.addUserInteraction((aq: Aquarium) =>
        aq.copy(aquariumState = aq.aquariumState.updateBrightness(brightness))
      )

    override def clean(): Unit =
      context.model.addUserInteraction((aq: Aquarium) => aq.copy(aquariumState = aq.aquariumState.updateImpurity(0)))

    override def updateOxygenation(oxygenation: Double): Unit =
      context.model.addUserInteraction((aq: Aquarium) =>
        aq.copy(aquariumState = aq.aquariumState.updateOxygenation(oxygenation))
      )

    override def addInhabitant[A](inhabitant: A): Unit =
      context.model.addUserInteraction((aq: Aquarium) => aq.copy(population = aq.population.addInhabitant(inhabitant)))

    override def removeInhabitant[A](inhabitant: A): Unit =
      context.model.addUserInteraction((aq: Aquarium) =>
        aq.copy(population = aq.population.removeInhabitant(inhabitant))
      )

    override def addFood(food: Food): Unit =
      context.model.addUserInteraction((aq: Aquarium) => aq.copy(availableFood = aq.availableFood.addFood(food)))

    override def getPopulationTrend(): List[(Int, Int, Int)] =
      (0 to simEngine.getIterations())
        .map(idx =>
          (
            context.model.getDatabase().getAllHerbivorousFish(idx).size,
            context.model.getDatabase().getAllCarnivorousFish(idx).size,
            context.model.getDatabase().getAllAlgae(idx).size
          )
        )
        .toList
