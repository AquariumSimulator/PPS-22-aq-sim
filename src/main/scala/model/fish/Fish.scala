package model.fish

import javafx.util.Pair
import model.Entity
import model.aquarium.{Aquarium, AquariumDimensions}

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
  val MEAT_AMOUNT: Int = 10
  val OXYGEN_SHIFT_CONSTANT: Double = -1.5
  val IMPURITY_SHIFT_CONSTANT: Double = 1.2
  val PH_SHIFT_CONSTANT: Double = -0.2

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
  var speed: (Double, Double) = (0.0, 0.0)

  /** Mono-dimensional size of the Fish */
  val size: Double = Random.between(MIN_SIZE, MAX_SIZE)

  var position: (Double, Double) = (0.0, 0.0)
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size
  val phShift: Double = PH_SHIFT_CONSTANT * size

  /** Utility method to check if the Fish is alive.
    *
    * @return
    *   True if hunger is positive, False otherwise.
    */
  def isAlive: Boolean = hunger > 0
  
  def changeSpeed(newSpeed: (Double, Double)): Unit = speed = newSpeed
  
  def move(): Unit =
    position = (position._1 + speed._1, position._2 + speed._2)
    position._1 match
      case x if x < 0 =>
        position = (x * -1, position._2)
      case x if x > AquariumDimensions.WIDTH =>
        position = (AquariumDimensions.WIDTH - (x - AquariumDimensions.WIDTH), position._2)
      case _ =>
    position._2 match
      case y if y < 0 =>
        position = (position._1, y * -1)
      case y if y > AquariumDimensions.HEIGHT =>
        position = (position._1, AquariumDimensions.HEIGHT - (y - AquariumDimensions.HEIGHT))
      case _ => 

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false
