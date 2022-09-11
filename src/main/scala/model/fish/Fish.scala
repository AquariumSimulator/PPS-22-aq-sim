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
  val PH_SHIFT_CONSTANT: Double = 0.2

/** Model definition of a Fish. */
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

  /** Unique name for each Fish */
  val name: String = "fish-" + n
  n = n + 1

  /** Hunger level of the Fish: starts at MAX_HUNGER and decreases over time */
  var hunger: Int = MAX_HUNGER

  /** Age fo the Fish: represents the number of iterations it has survived */
  val age: Int = 0

  /** Current movement speed of the Fish: (0,0) by default */
  val speed: (Double, Double) = (0.0, 0.0)

  /** Mono-dimensional size of the Fish */
  val size: Double = Random.between(MIN_SIZE, MAX_SIZE)

  val position: (Double, Double) = (0.0, 0.0)
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size
  val phShift: Double = PH_SHIFT_CONSTANT * size

  /** Utility method to check if the Fish is alive.
    *
    * @return
    *   True if hunger is positive, False otherwise.
    */
  def isAlive(): Boolean = hunger > 0

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false
