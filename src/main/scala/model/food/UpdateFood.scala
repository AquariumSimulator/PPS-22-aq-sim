package model.food

import model.aquarium.AquariumDimensions
import model.food.Food._

trait UpdateFood:

  def calculatePosition(): (Double, Double)
  def updatePosition(newPosition: (Double, Double)): Food
  def move(speedMultiplier: Double): Food

object UpdateFood:

  def apply(food: Food): UpdateFood = UpdateFoodImpl(food)

  private class UpdateFoodImpl(food: Food) extends UpdateFood:

    override def calculatePosition(): (Double, Double) =
      (food.position._1 + SPEED._1, food.position._2 + SPEED._2)

    override def updatePosition(newPosition: (Double, Double)): Food =
      food.copy(position = newPosition)

    override def move(speedMultiplier: Double): Food =
      var newPosition: (Double, Double) = calculatePosition()
      newPosition._2 match
        case y if y > AquariumDimensions.HEIGHT - food.size._2 =>
          newPosition = (newPosition._1, AquariumDimensions.HEIGHT - food.size._2)
        case _ =>
      updatePosition(newPosition)
