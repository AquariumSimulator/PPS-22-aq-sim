package controller

import mvc.ControllerModule.Requirements

/** Controller methods implementation from [[Controller]]. */
trait ControllerImpl:
  context: Requirements =>
  class ControllerImpl extends Controller:

    override def startSimulation(): Unit =
      SimulationEngine(context).start(SimulationSpeed.NORMAL)
