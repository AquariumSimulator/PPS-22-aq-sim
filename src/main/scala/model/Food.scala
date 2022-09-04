package model

import scala.util.Random

object Food:
  val MIN_NUTRITION_AMOUNT: Int = 5
  val MAX_NUTRITION_AMOUNT: Int = 15
  val IMPURITY_CONSTANT: Double = 0.3

trait Food extends Entity:
  
  import model.Food.{MAX_NUTRITION_AMOUNT, MIN_NUTRITION_AMOUNT, IMPURITY_CONSTANT}

  val nutritionAmount: Int = Random.between(MIN_NUTRITION_AMOUNT, MAX_NUTRITION_AMOUNT)

  def oxygenShift: Double = 0.0
  def impurityShift: Double = nutritionAmount * IMPURITY_CONSTANT
  def phShift: Double = 0.0

case class HerbivorousFood(position: (Double, Double) = (0.0, 0.0)) extends Food

case class CarnivorousFood(position: (Double, Double) = (0.0, 0.0)) extends Food
