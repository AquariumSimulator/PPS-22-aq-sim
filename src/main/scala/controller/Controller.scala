package controller

/** Controller trait implemented in [[ControllerImpl]]. */
trait Controller:

  /**
    * Start the simulation in a separated [[Thread]].
    * If simulation was halted, it resumes.
    */
  def startSimulation(): Unit

  /**
    * Halt the simulation.
    */
  def stopSimulation(): Unit

  /**
    * Change speed of simulation.
    *
    * @param simSpeed The new speed of simulation.
    */
  def changeSpeed(simSpeed: SimulationSpeed): Unit
