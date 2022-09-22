package controller

import mvc.ControllerModule.ControllerRequirements

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
