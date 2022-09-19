package controller

import mvc.ControllerModule.Requirements

/** Controller methods implementation from [[Controller]]. */
trait ControllerImpl:
  context: Requirements =>
  class ControllerImpl extends Controller:

    def notifyChange(s: String): Unit =
      context.view.show(context.model.m())

    override def startSimulation(): Unit =
      SimulationEngine(context).start()
