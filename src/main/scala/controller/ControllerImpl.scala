package controller

import mvc.ControllerModule.Requirements

/** Controller methods implementation from [[Controller]]. */
trait ControllerImpl:
  context: Requirements =>
  class ControllerImpl extends Controller:

    val simEngine: SimulationEngine = SimulationEngine(context)
    override def startSimulation(): Unit =
      simEngine.start(SimulationSpeed.NORMAL)

    override def stopSimulation(): Unit =
      simEngine.stop()

    override def changeSpeed(simSpeed: SimulationSpeed): Unit =
      simEngine.changeSpeed(simSpeed)
