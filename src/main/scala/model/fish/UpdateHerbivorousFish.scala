package model.fish

import model.HerbivorousFood
import model.aquarium.AquariumDimensions
import model.fish.Fish.{MAX_HUNGER, MEAT_AMOUNT}

trait UpdateHerbivorousFish:

  def updateHunger(newHunger: Int): HerbivorousFish
  def calculatePosition(): (Double, Double)
  def updatePosition(newPosition: (Double, Double)): HerbivorousFish
  def updateSpeed(newSpeed: (Double, Double)): HerbivorousFish
  def move(): HerbivorousFish
  def eat(food: HerbivorousFood): HerbivorousFish

object UpdateHerbivorousFish:

  def apply(fish: HerbivorousFish): UpdateHerbivorousFish = UpdateHerbivorousFishImpl(fish)

  private class UpdateHerbivorousFishImpl(fish: HerbivorousFish) extends UpdateHerbivorousFish:

    override def updateHunger(newHunger: Int): HerbivorousFish =
      fish.copy(hunger = newHunger)

    override def calculatePosition(): (Double, Double) =
      (fish.position._1 + fish.speed._1, fish.position._2 + fish.speed._2)

    override def updatePosition(newPosition: (Double, Double)): HerbivorousFish =
      fish.copy(position = newPosition)

    override def updateSpeed(newSpeed: (Double, Double)): HerbivorousFish =
      fish.copy(speed = newSpeed)

    override def move(): HerbivorousFish =
      var newPosition: (Double, Double) = calculatePosition()
      newPosition._1 match
        case x if x < 0 =>
          newPosition = (x * -1, newPosition._2)
        case x if x > AquariumDimensions.WIDTH =>
          newPosition = (AquariumDimensions.WIDTH - (x - AquariumDimensions.WIDTH), newPosition._2)
        case _ =>
      newPosition._2 match
        case y if y < 0 =>
          newPosition = (newPosition._1, y * -1)
        case y if y > AquariumDimensions.HEIGHT =>
          newPosition = (newPosition._1, AquariumDimensions.HEIGHT - (y - AquariumDimensions.HEIGHT))
        case _ =>
      updatePosition(newPosition)

    override def eat(food: HerbivorousFood): HerbivorousFish =
      fish.copy(hunger = MAX_HUNGER min fish.hunger + food.nutritionAmount)