package controller

import model.aquarium.Aquarium
import model.food.Food
import model.fish.Fish
import model.Algae

/** Controller trait implemented in [[ControllerImpl]]. */
trait Controller:

  /** Start the simulation in a separated [[Thread]]. If simulation was halted, it resumes. */
  def startSimulation(simSpeed: SimulationSpeed): Unit

  /** Halt the simulation. */
  def stopSimulation(): Unit

  /** Change speed of simulation.
    *
    * @param simSpeed
    *   The new speed of simulation.
    */
  def changeSpeed(simSpeed: SimulationSpeed): Unit

  /** Get speed of simulation. */
  def getSpeed(): SimulationSpeed

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

  /** Updates the temperature of the aquarium
    * @param temperature
    *   the new temperature value
    */
  def updateTemperature(temperature: Double): Unit

  /** Updates the brightness of the aquarium
    * @param brightness
    *   the new brightness value
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */

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

  /** Add a new food in the right food set
    * @param food
    *   the food that needs to be added
    */
  def addFood(food: Food): Unit

  /** @return
    *   Returns the population of the aquarium at each iteration. Each i-th element of the returned List corresponds to
    *   the population at the i-th instant. Each i-th element is composed of, in order:
    *   - the number of Herbivorous Fish
    *   - the number of Carnivorous Fish
    *   - the number of Algae
    */
  def getPopulationTrend(): List[(Int, Int, Int)]
