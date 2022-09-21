package model.fish

import javafx.util.Pair
import model.Entity
import model.aquarium.{Aquarium, AquariumDimensions}
import model.fish.Fish.*
import scala.util.Random
import model.FeedingType

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
  val MEAT_AMOUNT: Int = 10
  val OXYGEN_SHIFT_CONSTANT: Double = -0.2
  val IMPURITY_SHIFT_CONSTANT: Double = 0.1
  val PH_SHIFT_CONSTANT: Double = 0.2

  def getAndIncrementN(): Int =
    n = n + 1
    n

case class Fish(
    name: String = "fish-" + getAndIncrementN(),
    hunger: Int = MAX_HUNGER,
    age: Int = 0,
    speed: (Double, Double) = (0.0, 0.0),
    size: Double = Random.between(MIN_SIZE, MAX_SIZE),
    position: (Double, Double) = (0.0, 0.0),
    feedingType: FeedingType = FeedingType.CARNIVOROUS
) extends Entity:
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size
  val phShift: Double = PH_SHIFT_CONSTANT * size

  def isAlive: Boolean = hunger > 0

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false
