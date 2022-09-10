package model.fish

import model.CarnivorousFood
import model.aquarium.AquariumDimensions
import model.fish.Fish.{MAX_HUNGER, MEAT_AMOUNT}

trait UpdateCarnivorousFish:

  def calculatePosition(): (Double, Double)
  def updatePosition(newPosition: (Double, Double)): CarnivorousFish
  def updateSpeed(newSpeed: (Double, Double)): CarnivorousFish
  def move(): CarnivorousFish
  def eat(fish: Fish): CarnivorousFish
  def eat(food: CarnivorousFood): CarnivorousFish

object UpdateCarnivorousFish:
  
  def apply(fish: CarnivorousFish): UpdateCarnivorousFish = UpdateCarnivorousFishImpl(fish)
    
  private class UpdateCarnivorousFishImpl(fish: CarnivorousFish) extends UpdateCarnivorousFish:

    override def calculatePosition(): (Double, Double) =
      (fish.position._1 + fish.speed._1, fish.position._2 + fish.speed._2)

    override def updatePosition(newPosition: (Double, Double)): CarnivorousFish =
      fish.copy(position = newPosition)

    override def updateSpeed(newSpeed: (Double, Double)): CarnivorousFish =
      fish.copy(speed = newSpeed)

    override def move(): CarnivorousFish =
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

    override def eat(ateFish: Fish): CarnivorousFish =
      fish.copy(hunger = MAX_HUNGER min fish.hunger + (MEAT_AMOUNT * ateFish.size).floor.toInt)

    override def eat(food: CarnivorousFood): CarnivorousFish =
      fish.copy(hunger = MAX_HUNGER min fish.hunger + food.nutritionAmount)