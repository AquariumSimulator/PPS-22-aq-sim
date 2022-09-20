package model.aquarium

import model.food.{CarnivorousFood, HerbivorousFood}

/** This case class represent the food available inside the aquarium
  *
  * @param herbivorousFood
  *   herbivorous food available
  * @param carnivorousFood
  *   carnivorous food available
  */
case class AvailableFood(
    herbivorousFood: Set[HerbivorousFood] = Set.empty,
    carnivorousFood: Set[CarnivorousFood] = Set.empty
) extends UpdateAvailableFood:
  override def addFood[A](addElem: A): AvailableFood =
    addElem match
      case e: HerbivorousFood => this.copy(herbivorousFood = this.herbivorousFood + e)
      case e: CarnivorousFood => this.copy(carnivorousFood = this.carnivorousFood + e)

  override def deleteFood[A](removeElem: A): AvailableFood =
    removeElem match
      case e: HerbivorousFood => this.copy(herbivorousFood = this.herbivorousFood.filterNot(elem => elem == e))
      case e: CarnivorousFood => this.copy(carnivorousFood = this.carnivorousFood.filterNot(elem => elem == e))
