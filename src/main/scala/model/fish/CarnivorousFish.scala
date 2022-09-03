package model.fish

import model.CarnivorousFood

case class CarnivorousFish() extends Fish:

  import model.fish.Fish.{MEAT_AMOUNT, MAX_HUNGER}

  def eat(fish: Fish): Unit =
    hunger = MAX_HUNGER min hunger + (MEAT_AMOUNT * fish.size).floor.toInt

  def eat(food: CarnivorousFood): Unit =
    hunger = MAX_HUNGER min hunger + food.NUTRITION_AMOUNT
