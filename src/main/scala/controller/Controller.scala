package controller

import model.aquarium.Aquarium

/** Controller trait implemented in [[ControllerImpl]]. */
trait Controller:

  /** Start the simulation in a separated [[Thread]]. If simulation was halted, it resumes. */
  def startSimulation(): Unit

  /** Halt the simulation. */
  def stopSimulation(): Unit

  /** Change speed of simulation.
    *
    * @param simSpeed
    *   The new speed of simulation.
    */
  def changeSpeed(simSpeed: SimulationSpeed): Unit

  /** Check if the simulation is currently running or not.
    *
    * @return
    *   True if simulation is running.
    */
  def isRunning(): Boolean

  /** Get the current [[Aquarium]].
    *
    * @return
    *   The [[Aquarium]].
    */
  def getAquarium(): Aquarium

  /** Set the current aquarium
    *
    * @param updatedAquarium
    *   new current aquarium
    */
  def setAquarium(updatedAquarium: Aquarium): Unit
