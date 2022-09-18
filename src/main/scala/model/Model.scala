package model

import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.fish.Fish

/** Model trait implemented in [[ModelImpl]]. */
trait Model:

  def m(): Int

  /** Create the initial aquarium with a default [[AquariumState]], a [[Population]] initialized by the user and a
    * default [[AvailableFood]]
    * @param herbivorousFishNumber
    *   number of initial herbivorous fish
    * @param carnivorousFishNumber
    *   number of initial carnivorous fish
    * @param algaeNumber
    *   number of initial algae
    * @return
    *   a new [[Aquarium]]
    */
  def initializeAquarium(herbivorousFishNumber: Int, carnivorousFishNumber: Int, algaeNumber: Int): Aquarium

  /** Updates the temperature of the aquarium taking in input the old one and returning a new one with the new
    * temperature.
    * @param temperature
    *   the new temperature value
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */
  def updateTemperatureByUser(temperature: Double, aquarium: Aquarium): Aquarium

  /** Updates the brightness of the aquarium taking in input the old one and returning a new one with the new
    * brightness.
    * @param brightness
    *   the new brightness value
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */
  def updateBrightnessByUser(brightness: Double, aquarium: Aquarium): Aquarium

  /** Updates the impurity of the aquarium taking in input the old one and returning a new one with the impurity set to
    * 0.
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */
  def cleanByUser(aquarium: Aquarium): Aquarium

  /** Updates the oxygenation of the aquarium taking in input the old one and returning a new one with the new
    * oxygenation.
    * @param oxygenation
    *   the new oxygenation value
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */
  def updateOxygenationByUser(oxygenation: Double, aquarium: Aquarium): Aquarium

  /** Add a new inhabitant in the right population set taking in input the old aquarium and returning a new one.
    * @param aquarium
    *   the current aquarium
    * @param inhabitant
    *   the inhabitant that needs to be added
    * @tparam A
    *   type of the inhabitant
    * @return
    *   a new updated aquarium
    */
  def addInhabitant[A](aquarium: Aquarium, inhabitant: A): Aquarium

  /** Remove an inhabitant in the right population set taking in input the old aquarium and returning a new one.
    * @param aquarium
    *   the current aquarium
    * @param inhabitant
    *   the inhabitant that needs to be removed
    * @tparam A
    *   type of the inhabitant
    * @return
    *   a new updated aquarium
    */
  def removeInhabitant[A](aquarium: Aquarium, inhabitant: A): Aquarium

  /** Add a new food in the right food set taking in input the old aquarium and returning a new one.
    * @param aquarium
    *   the current aquarium
    * @param food
    *   the food that needs to be added
    * @tparam A
    *   type of the inhabitant
    * @return
    *   a new updated aquarium
    */
  def addFood[A](aquarium: Aquarium, food: A): Aquarium

  /** Remove an food in the right food set taking in input the old aquarium and returning a new one.
    * @param aquarium
    *   the current aquarium
    * @param food
    *   the food that needs to be removed
    * @tparam A
    *   type of the inhabitant
    * @return
    *   a new updated aquarium
    */
  def removeFood[A](aquarium: Aquarium, food: A): Aquarium

  /** Step of the simulation
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */
  def step(aquarium: Aquarium): Aquarium
