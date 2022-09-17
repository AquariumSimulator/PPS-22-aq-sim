package controller

/** Controller trait implemented in [[ControllerImpl]]. */
trait Controller:

  def notifyChange(s: String): Unit
