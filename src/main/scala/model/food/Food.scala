package model.food

import scala.util.Random
import model.Entity
import model.FeedingType
import model.aquarium.{AquariumDimensions => aqDim}

object Food:
  val MIN_NUTRITION_AMOUNT: Int = 5
  val MAX_NUTRITION_AMOUNT: Int = 15
  val IMPURITY_CONSTANT: Double = 0.3
  val SPEED: (Double, Double) = (0.0, 0.1)

case class Food(
    position: (Double, Double) = (Random.between(0, aqDim.WIDTH), 0),
    nutritionAmount: Int = Random.between(MIN_NUTRITION_AMOUNT, MAX_NUTRITION_AMOUNT),
    feedingType: FeedingType = FeedingType.CARNIVOROUS
) extends Entity:

  import Food._

  val size: (Double, Double) = (5, 5)
  val oxygenShift: Double = 0.0
  val impurityShift: Double = nutritionAmount * IMPURITY_CONSTANT
  val phShift: Double = 0.0
