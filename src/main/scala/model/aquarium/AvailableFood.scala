package model.aquarium

import model.FeedingType
import model.food.Food

/** This case class represent the food available inside the aquarium
  *
  * @param herbivorousFood
  *   herbivorous food available
  * @param carnivorousFood
  *   carnivorous food available
  */
case class AvailableFood(
    herbivorousFood: Set[Food] = Set.empty,
    carnivorousFood: Set[Food] = Set.empty
) extends UpdateAvailableFood:
  override def addFood(addElem: Food): AvailableFood =
    addElem match
      case e if e.feedingType == FeedingType.HERBIVOROUS => this.copy(herbivorousFood = this.herbivorousFood + e)
      case e => this.copy(carnivorousFood = this.carnivorousFood + e)

  override def deleteFood(removeElem: Food): AvailableFood =
    removeElem match
      case e if e.feedingType == FeedingType.HERBIVOROUS =>
        this.copy(herbivorousFood = this.herbivorousFood.filterNot(elem => elem == e))
      case e => this.copy(carnivorousFood = this.carnivorousFood.filterNot(elem => elem == e))
