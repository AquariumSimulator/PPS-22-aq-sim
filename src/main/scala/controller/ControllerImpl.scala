package controller

import mvc.ControllerModule.Requirements
import controller.Controller

/** Controller methods implementation from [[Controller]]. */
trait ControllerImpl:
  context: Requirements =>
  class ControllerImpl extends Controller:

    def notifyChange(s: String): Unit = ???
