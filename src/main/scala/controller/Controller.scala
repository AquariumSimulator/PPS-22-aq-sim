package controller

import model.Algae
import model.aquarium.Aquarium
import model.chronicle.Chronicle
import model.fish.Fish
import model.food.Food

/** Controller trait implemented in [[ControllerImpl]]. */
trait Controller:

  /** Start the simulation in a separated [[Thread]]. If simulation was halted, it resumes. If simulation was haltted
    * and simSpeed is HALT then an only one step is taken.
    */
  def startSimulation(simSpeed: SimulationSpeed): Unit

  /** Pause the simulation. */
  def pauseSimulation(): Unit

  /** Halt the simulation. */
  def stopSimulation(): Unit

  /** Change speed of simulation.
    *
    * @param simSpeed
    *   The new speed of simulation.
    */
  def changeSpeed(simSpeed: SimulationSpeed): Unit

  /** Get speed of simulation. */
  def getSpeed: SimulationSpeed

  /** Check if the simulation is currently running or not.
    *
    * @return
    *   True if simulation is running.
    */
  def isRunning: Boolean

  /** Get the current [[Aquarium]].
    *
    * @return
    *   The [[Aquarium]].
    */
  def getAquarium: Aquarium

  /** Updates the temperature of the aquarium
    * @param temperature
    *   the new temperature value
    */
  def updateTemperature(temperature: Double): Unit

  /** Updates the brightness of the aquarium
    * @param brightness
    *   the new brightness value
    */
  def updateBrightness(brightness: Double): Unit

  /** Updates the impurity of the aquarium setting the impurity set to 0 */
  def clean(): Unit

  /** Updates the impurity of the aquarium setting the impurity set to 0
    * @param oxygenation
    *   the new oxygenation value
    */
  def updateOxygenation(oxygenation: Double): Unit

  /** Add a new inhabitant in the right population set
    * @param inhabitant
    *   the inhabitant that needs to be added
    * @tparam A
    *   type of the inhabitant
    */
  def addInhabitant[A](inhabitant: A): Unit

  /** Remove an inhabitant in the right population set
    * @param inhabitant
    *   the inhabitant that needs to be removed
    * @tparam A
    *   type of the inhabitant
    */
  def removeInhabitant[A](inhabitant: A): Unit

  /** Add a new food in the food set
    * @param food
    *   the food that needs to be added
    */
  def addFood(food: Food): Unit

  /** Remove food in the food set
    * @param food
    *   the food that needs to be removed
    */
  def deleteFood(food: Food): Unit

  /** @return
    *   Returns the population of the aquarium at each iteration. Each i-th element of the returned List corresponds to
    *   the population at the i-th instant. Each i-th element is composed of, in order:
    *   - the number of Herbivorous Fish
    *   - the number of Carnivorous Fish
    *   - the number of Algae
    */
  def getPopulationTrend: List[(Int, Int, Int)]

  /** Return the current Chronicle
    * @return
    *   current Chronicle
    */
  def getCurrentChronicle: Chronicle

  /** Get all [[Fish]].
    *
    * @param iteration
    *   of fish to get.
    * @return
    *   a [[List]] of [[Fish]].
    */
  def getAllFish(iteration: Int): List[Fish]

  /** Get all [[Algae]].
    *
    * @param iteration
    *   of algae to get.
    * @return
    *   a [[Algae]] of [[Algae]].
    */
  def getAllAlgae(iteration: Int): List[Algae]

  /** Get the current iteration.
    *
    * @return
    *   the current iteration as an [[Int]].
    */
  def currentIteration: Int
