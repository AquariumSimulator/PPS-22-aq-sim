package model.fish

import model.Entity
import model.aquarium.{Aquarium, AquariumDimensions}
import model.fish.Fish.*
import scala.util.Random
import model.FeedingType

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100
  val MIN_SIZE: Double = 5
  val MAX_SIZE: Double = 20
  val MEAT_AMOUNT: Double = 0.05
  val HUNGER_SHIFT: Int = 1
  val OXYGEN_SHIFT_CONSTANT: Double = -0.01
  val IMPURITY_SHIFT_CONSTANT: Double = 0.001
  val PH_SHIFT_CONSTANT: Double = 0.01
  val MAX_REPRODUCTION_FACTOR: Int = 100
  val REPRODUCTION_FACTOR_SHIFT: Int = 5
  val REPRODUCTION_COST: Int = 50

  def getAndIncrementN(): Int =
    n = n + 1
    n

case class Fish(
    name: String = "fish-" + getAndIncrementN(),
    hunger: Int = MAX_HUNGER,
    age: Int = 0,
    speed: (Double, Double) = (0.0, 0.0),
    size: (Double, Double) = (Random.between(MIN_SIZE, MAX_SIZE), Random.between(MIN_SIZE, MAX_SIZE)),
    position: (Double, Double) = (0.0, 0.0),
    feedingType: FeedingType = FeedingType.CARNIVOROUS,
    reproductionFactor: Int = 30
) extends Entity:
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size._1
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size._1
  val phShift: Double = PH_SHIFT_CONSTANT * size._1

  def isAlive: Boolean = hunger > 0

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false
