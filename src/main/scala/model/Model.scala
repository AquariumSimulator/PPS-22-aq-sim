package model

import model.aquarium.{Aquarium, AquariumState, AvailableFood}
import model.chronicle.Chronicle
import model.db.PrologEngine
import model.fish.Fish
import model.food.Food

/** Model trait implemented in [[ModelImpl]]. */
trait Model:
  /** Return the current chronicle
    * @return
    *   the current chronicle
    */
  def chronicle: Chronicle

  /** Add a new event to the current chronicle
    * @param event
    *   event to add to the current chronicle
    */
  def addChronicleEvent(event: String): Unit

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

  /** Saves the population of the given [[Aquarium]] in the database.
    *
    * @param aquarium
    *   The [[Aquarium]] to be saved.
    * @param iteration
    *   The iteration the [[Aquarium]] object refers to.
    */
  def saveAquarium(aquarium: Aquarium, iteration: Int): Unit

  /** @return
    *   A reference to the Database used by the simulation.
    */
  def getDatabase(): PrologEngine
