package model.fish

import model.HerbivorousFood

case class HerbivorousFish() extends Fish:

  import model.fish.Fish.MAX_HUNGER

  def eat(food: HerbivorousFood): Unit =
    hunger = MAX_HUNGER min hunger + food.NUTRITION_AMOUNT
