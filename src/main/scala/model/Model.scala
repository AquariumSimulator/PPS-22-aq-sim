package model

import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.fish.Fish
import model.food.Food

/** Model trait implemented in [[ModelImpl]]. */
trait Model:

  def addUserInteraction(interaction: Aquarium => Aquarium): Unit

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

  /** Step of the simulation
    * @param aquarium
    *   the current aquarium
    * @return
    *   a new updated aquarium
    */
  def step(aquarium: Aquarium): Aquarium
