package model.aquarium

import model.{CarnivorousFood, Food, HerbivorousFood}

/** This case class represent the food available inside the aquarium
  *
  * @param herbivorousFood
  *   herbivorous food available
  * @param carnivorousFood
  *   carnivorous food available
  */
case class AvailableFood(herbivorousFood: Set[Food], carnivorousFood: Set[Food])