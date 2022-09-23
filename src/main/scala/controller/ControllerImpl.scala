package controller

import mvc.ControllerModule.ControllerRequirements
import model.aquarium.Aquarium
import mvc.MVC.{given_ControllerRequirements => context}

/** Controller methods implementation from [[Controller]]. */
trait ControllerImpl:
  context: ControllerRequirements =>
  given ControllerRequirements = context
  class ControllerImpl extends Controller:

    val simEngine: SimulationEngine = SimulationEngine(context.model.initializeAquarium(10, 10, 10))
    override def startSimulation(): Unit =
      simEngine.start(SimulationSpeed.NORMAL)

    override def stopSimulation(): Unit =
      simEngine.stop()

    override def changeSpeed(simSpeed: SimulationSpeed): Unit =
      simEngine.changeSpeed(simSpeed)

    override def isRunning(): Boolean =
      simEngine.isRunning()

    override def getAquarium(): Aquarium =
      simEngine.getAquarium()
