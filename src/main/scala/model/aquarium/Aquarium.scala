package model.aquarium

import model.FeedingType
import model.food.Food

import scala.annotation.tailrec

/** This class represent the aquarium
  * @param aquariumState
  *   represent the state of the aquarium
  * @param population
  *   represent the population of the aquarium
  * @param availableFood
  *   represent the food amount available in the aquarium
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
