package controller

import model.Entity
import model.aquarium.{Aquarium, InitializeAquarium}
import model.chronicle.{Chronicle, Events}
import model.food.Food
import model.fish.Fish
import model.Algae
import mvc.ControllerModule.ControllerRequirements

/** Controller methods implementation from [[Controller]]. */
trait ControllerComponent:
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

    override def pauseSimulation(): Unit =
      simEngine.pause()

    override def changeSpeed(simSpeed: SimulationSpeed): Unit =
      context.model.addChronicleEvent(Events.CHANGED_SPEED(simSpeed.toString))
      simEngine.changeSpeed(simSpeed)

    override def getSpeed: SimulationSpeed =
      simEngine.getSpeed()

    override def isRunning: Boolean =
      simEngine.isRunning()

    override def getAquarium: Aquarium =
      simEngine.getAquarium()

    private def addUserInteraction(interaction: Aquarium => Aquarium): Unit =
      context.model.addUserInteraction(interaction)
      simEngine.isRunning() match
        case false => simEngine.start(SimulationSpeed.HALT)
        case _ =>

    override def updateTemperature(temperature: Double): Unit =
      context.model.addChronicleEvent(Events.UPDATED_AQUARIUM_STATE_PARAMETER(temperature, "Temperature", "°"))
      addUserInteraction((aq: Aquarium) => aq.copy(aquariumState = aq.aquariumState.updateTemperature(temperature)))

    override def updateBrightness(brightness: Double): Unit =
      context.model.addChronicleEvent(Events.UPDATED_AQUARIUM_STATE_PARAMETER(brightness, "Brightness", "%"))
      addUserInteraction((aq: Aquarium) => aq.copy(aquariumState = aq.aquariumState.updateBrightness(brightness)))

    override def clean(): Unit =
      context.model.addChronicleEvent(Events.CLEAN_AQUARIUM)
      addUserInteraction((aq: Aquarium) => aq.copy(aquariumState = aq.aquariumState.updateImpurity(0)))

    override def updateOxygenation(oxygenation: Double): Unit =
      context.model.addChronicleEvent(Events.UPDATED_AQUARIUM_STATE_PARAMETER(oxygenation, "Oxygenation", "mg/L"))
      addUserInteraction((aq: Aquarium) => aq.copy(aquariumState = aq.aquariumState.updateOxygenation(oxygenation)))

    override def addInhabitant[A](inhabitant: A): Unit =
      context.model.addChronicleEvent(Events.ADDED_ENTITY(inhabitant))
      addUserInteraction((aq: Aquarium) => aq.copy(population = aq.population.addInhabitant(inhabitant)))

    override def removeInhabitant[A](inhabitant: A): Unit =
      context.model.addChronicleEvent(Events.REMOVED_ENTITY(inhabitant))
      addUserInteraction((aq: Aquarium) => aq.copy(population = aq.population.removeInhabitant(inhabitant)))

    override def addFood(food: Food): Unit =
      context.model.addChronicleEvent(Events.ADDED_ENTITY(food))
      addUserInteraction((aq: Aquarium) => aq.addFood(food))

    override def deleteFood(food: Food): Unit =
      context.model.addChronicleEvent(Events.REMOVED_ENTITY(food))
      addUserInteraction((aq: Aquarium) => aq.deleteFood(food))

    override def getPopulationTrend: List[(Int, Int, Int)] =
      (0 to simEngine.getIterations())
        .map(idx =>
          (
            context.model.getDatabase.getAllHerbivorousFish(idx).size,
            context.model.getDatabase.getAllCarnivorousFish(idx).size,
            context.model.getDatabase.getAllAlgae(idx).size
          )
        )
        .toList

    override def getCurrentChronicle: Chronicle =
      context.model.chronicle

    override def getAllFish(iteration: Int): List[Fish] =
      context.model.getDatabase.getAllFish(iteration)

    override def getAllAlgae(iteration: Int): List[Algae] =
      context.model.getDatabase.getAllAlgae(iteration)

    override def currentIteration: Int =
      simEngine.getIterations()
