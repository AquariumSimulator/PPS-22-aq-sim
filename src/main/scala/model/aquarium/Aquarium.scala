package model.aquarium

import model.FeedingType
import model.food.Food

import scala.annotation.tailrec

/** This class represents the aquarium
  * @param aquariumState
  *   represents the state of the aquarium
  * @param population
  *   represents the population of the aquarium
  * @param availableFood
  *   represents the food amount available in the aquarium
  */
case class Aquarium(
    aquariumState: AquariumState,
    population: Population,
    override val availableFood: Set[Food] = Set.empty
) extends AvailableFood
    with UpdateAvailableFood:

  override def addFood(food: Food): Aquarium =
    this.copy(availableFood = this.availableFood + food)

  override def deleteFood(food: Food): Aquarium =
    this.copy(availableFood = this.availableFood - food)

/** Companion object of the case class [[Aquarium]] */
object Aquarium:

  /** Create a new [[Aquarium]]
    *
    * @param herbivorousFishNumber
    *   number of herbivorous fishes
    * @param carnivorousFishNumber
    *   number of carnivorous fishes
    * @param algaeNumber
    *   number of algae
    * @return
    *   an instance of the aquarium
    */
  def apply(herbivorousFishNumber: Int, carnivorousFishNumber: Int, algaeNumber: Int): Aquarium =
    Aquarium(
      AquariumState(),
      Population(herbivorousFishNumber, carnivorousFishNumber, algaeNumber),
      Set.empty
    )
