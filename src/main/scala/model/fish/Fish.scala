package model.fish

import model.Entity
import model.aquarium.{Aquarium, AquariumDimensions}
import model.fish.Fish.*
import scala.util.Random
import model.FeedingType

object Fish:
  var n: Int = 0
  val MAX_SATIETY: Int = 100
  val SATIETY_SHIFT: Int = 1
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
  val MEAT_AMOUNT: Int = 10
  val OXYGEN_SHIFT_CONSTANT: Double = -0.02
  val IMPURITY_SHIFT_CONSTANT: Double = 0.01
  val PH_SHIFT_CONSTANT: Double = 0.1
  val MAX_REPRODUCTION_FACTOR: Int = 100
  val REPRODUCTION_FACTOR_SHIFT: Int = 5
  val REPRODUCTION_COST: Int = 50
  val MAX_SPEED: Int = 5
  val MIN_SPEED: Int = 1

  def getAndIncrementN(): Int =
    n = n + 1
    n

case class Fish(
                   name: String = "fish-" + getAndIncrementN(),
                   satiety: Int = MAX_SATIETY,
                   age: Int = 0,
                   speed: (Double, Double) = (0.0, 0.0),
                   size: Double = Random.between(MIN_SIZE, MAX_SIZE),
                   position: (Double, Double) = (0.0, 0.0),
                   feedingType: FeedingType = FeedingType.CARNIVOROUS,
                   reproductionFactor: Int = 30
) extends Entity:
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size
  val phShift: Double = PH_SHIFT_CONSTANT * size

  def isAlive: Boolean = satiety > 0

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false
