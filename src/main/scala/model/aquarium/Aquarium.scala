package model.aquarium

import model.FeedingType
import model.food.Food

/** This class represent the current state of the simulation
  * @param aquariumState
  *   represent the current state of the aquarium
  * @param population
  *   represent the current population of the aquarium
  * @param availableFood
  *   represent the current food amount available in the aquarium
  */
case class Aquarium(
    aquariumState: AquariumState,
    population: Population,
    override val availableFood: Set[Food] = Set.empty
) extends AvailableFood

/** Companion object of the case class */
object Aquarium:

  /** Create a new [[Aquarium]]
    *
    * @param herbivorousFishesNumber
    *   number of herbivorous fishes
    * @param carnivorousFishesNumber
    *   number of carnivorous fishes
    * @param algaeNumber
    *   number of algae
    * @return
    *   an instance of the aquarium
    */
  def apply(herbivorousFishesNumber: Int, carnivorousFishesNumber: Int, algaeNumber: Int): Aquarium =
    Aquarium(
      AquariumState(),
      Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber),
      Set.empty
    )
