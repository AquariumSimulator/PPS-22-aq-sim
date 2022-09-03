package model.fish

import model.Entity

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
  val MEAT_AMOUNT: Int = 10
  val OXYGEN_SHIFT_CONSTANT: Double = -1.5
  val IMPURITY_SHIFT_CONSTANT: Double = 1.2
  val PH_SHIFT_CONSTANT: Double = -0.2

trait Fish extends Entity:

  import scala.util.Random
  import model.fish.Fish.{
    MAX_HUNGER,
    n,
    MIN_SIZE,
    MAX_SIZE,
    OXYGEN_SHIFT_CONSTANT,
    IMPURITY_SHIFT_CONSTANT,
    PH_SHIFT_CONSTANT
  }

  val name: String = "fish-" + n
  n = n + 1

  var hunger: Int = MAX_HUNGER
  val age: Int = 0
  val speed: (Double, Double) = (0.0, 0.0)
  val size: Double = Random.between(MIN_SIZE, MAX_SIZE)
  val position: (Double, Double) = (0.0, 0.0)
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size
  val phShift: Double = PH_SHIFT_CONSTANT * size

  def isAlive: Boolean = hunger > 0
