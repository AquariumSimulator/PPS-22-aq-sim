package aquarium

import fish.{CarnivorousFood, HerbivorousFood}

/** This case class represent the food available inside the aquarium
  *
  * @param herbivorousFood
  *   herbivorous food available
  * @param carnivorousFood
  *   carnivorous food available
  */
case class AvailableFood(herbivorousFood: Set[HerbivorousFood], carnivorousFood: Set[CarnivorousFood])
