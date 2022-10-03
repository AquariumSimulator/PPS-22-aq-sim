package model.fish

import model.Entity
import model.aquarium.{Aquarium, AquariumDimensions}
import model.fish.Fish.*
import scala.util.Random
import model.FeedingType

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100
  val MAX_HEIGHT: Double = 10
  val MAX_WIDTH: Double = 20
  val MEAT_AMOUNT: Double = 0.6
  val HUNGER_SHIFT: Int = 1
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
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
    satiety: Int = MAX_HUNGER,
    age: Int = 0,
    speed: (Double, Double) = (0.0, 0.0),
    size: (Double, Double) = (Random.between(MIN_SIZE, MAX_WIDTH), Random.between(MIN_SIZE, MAX_HEIGHT)),
    position: (Double, Double) = (0.0, 0.0),
    feedingType: FeedingType = FeedingType.CARNIVOROUS,
    reproductionFactor: Int = 30
) extends Entity:
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size._1
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size._1
  val phShift: Double = PH_SHIFT_CONSTANT * size._1

  def isAlive: Boolean =
    val a = satiety > 0
    if !a then println(this.name + " è morto di fame")
    a

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false
