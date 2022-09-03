package aquarium

import model.{CarnivorousFood, HerbivorousFood}

/** This case class represent the food available inside the aquarium
  *
  * @param herbivorousFood
  *   herbivorous food available
  * @param carnivorousFood
  *   carnivorous food available
  */
case class AvailableFood(herbivorousFood: Seq[HerbivorousFood], carnivorousFood: Seq[CarnivorousFood])
