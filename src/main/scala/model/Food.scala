package model

import scala.util.Random

object Food:
  val MIN_NUTRITION_AMOUNT: Int = 5
  val MAX_NUTRITION_AMOUNT: Int = 15

trait Food:

  import model.Food.{MIN_NUTRITION_AMOUNT, MAX_NUTRITION_AMOUNT}

  val NUTRITION_AMOUNT: Int = Random.between(MIN_NUTRITION_AMOUNT, MAX_NUTRITION_AMOUNT)

case class HerbivorousFood() extends Food

case class CarnivorousFood() extends Food
