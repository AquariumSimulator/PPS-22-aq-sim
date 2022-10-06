package model

import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.fish.Fish
import model.food.Food
import model.db.PrologEngine

/** Model trait implemented in [[ModelImpl]]. */
trait Model:

  /** Add an interaction from the user to be processed.
    *
    * @param interaction
    *   the user interaction.
    */
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

  /** @return
    *   A reference to the Database used by the simulation.
    */
  def getDatabase(): PrologEngine
