package model

import scala.util.Random

object Food:
  val MIN_NUTRITION_AMOUNT: Int = 5
  val MAX_NUTRITION_AMOUNT: Int = 15

trait Food:

  import model.Food.{MAX_NUTRITION_AMOUNT, MIN_NUTRITION_AMOUNT}

  val NUTRITION_AMOUNT: Int = Random.between(MIN_NUTRITION_AMOUNT, MAX_NUTRITION_AMOUNT)

case class HerbivoreFood() extends Food

case class CarnivoreFood() extends Food
