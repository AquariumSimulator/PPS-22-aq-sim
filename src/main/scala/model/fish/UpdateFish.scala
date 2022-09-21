package model.fish

import model.aquarium.AquariumDimensions
import model.fish.Fish.{MAX_HUNGER, MEAT_AMOUNT}
import model.food.Food

trait UpdateFish:

  def calculatePosition(): (Double, Double)
  def updatePosition(newPosition: (Double, Double)): Fish
  def updateSpeed(newSpeed: (Double, Double)): Fish
  def updateHunger(newHunger: Int): Fish

  def move(speedMultiplier: Double): Fish
  def eat(fish: Fish): Fish
  def eat(food: Food): Fish

object UpdateFish:

  def apply(fish: Fish): UpdateFish = UpdateFishImpl(fish)

  private class UpdateFishImpl(fish: Fish) extends UpdateFish:

    override def calculatePosition(): (Double, Double) =
      (fish.position._1 + fish.speed._1, fish.position._2 + fish.speed._2)

    override def updateHunger(newHunger: Int): Fish =
      fish.copy(hunger = newHunger)

    override def updateSpeed(newSpeed: (Double, Double)): Fish =
      fish.copy(speed = newSpeed)

    override def updatePosition(newPosition: (Double, Double)): Fish =
      fish.copy(position = newPosition)

    override def move(speedMultiplier: Double): Fish =
      var newPosition: (Double, Double) = calculatePosition()
      var newSpeed: (Double, Double) = (fish.speed._1 * speedMultiplier, fish.speed._2 * speedMultiplier)
      println("multiplier -> " + speedMultiplier)
      println("origin speed -> " + fish.speed)
      println("new speed -> " + newSpeed)
      newPosition._1 match
        case x if x < 0 =>
          newPosition = (x * -1, newPosition._2)
          newSpeed = (newSpeed._1 * -1, newSpeed._2)
        case x if x > AquariumDimensions.WIDTH =>
          newPosition = (AquariumDimensions.WIDTH - (x - AquariumDimensions.WIDTH), newPosition._2)
          newSpeed = (newSpeed._1 * -1, newSpeed._2)
        case _ =>
      newPosition._2 match
        case y if y < 0 =>
          newPosition = (newPosition._1, y * -1)
          newSpeed = (newSpeed._1, newSpeed._2 * -1)
        case y if y > AquariumDimensions.HEIGHT =>
          newPosition = (newPosition._1, AquariumDimensions.HEIGHT - (y - AquariumDimensions.HEIGHT))
          newSpeed = (newSpeed._1, newSpeed._2 * -1)
        case _ =>
      updatePosition(newPosition)

    override def eat(ateFish: Fish): Fish =
      fish.copy(hunger = MAX_HUNGER min fish.hunger + (MEAT_AMOUNT * ateFish.size).floor.toInt)

    override def eat(food: Food): Fish =
      fish.copy(hunger = MAX_HUNGER min fish.hunger + food.nutritionAmount)
