package model.fish

import model.Entity
import model.aquarium.{Aquarium, AquariumDimensions}
import model.fish.Fish.*

import scala.util.Random
import model.FeedingType
import model.food.Food
import model.interaction.DeathProbabilityFish
import model.chronicle.Events
import mvc.MVC.model

/** The class represent the fishes of the simulation
  * @param name
  *   represents the name of the fish
  * @param satiety
  *   represents the satiety of the fish
  * @param age
  *   represents the age of the fish
  * @param speed
  *   represents the speed of the fish
  * @param size
  *   represents the size of the fish
  * @param position
  *   represents the position of the fish inside the aquarium
  * @param feedingType
  *   represents the feeding type of the fish
  * @param reproductionFactor
  *   represents the factor of reproduction of the fish
  */
case class Fish(
    name: String = "fish-" + getAndIncrementN(),
    satiety: Int = MAX_SATIETY,
    age: Int = 0,
    speed: (Double, Double) = (0.0, 0.0),
    size: (Double, Double) = (Random.between(MIN_SIZE, MAX_WIDTH), Random.between(MIN_SIZE, MAX_HEIGHT)),
    position: (Double, Double) = (0.0, 0.0),
    feedingType: FeedingType = FeedingType.CARNIVOROUS,
    reproductionFactor: Int = 30
) extends Entity
    with UpdateFish:
  val oxygenShift: Double = OXYGEN_SHIFT_CONSTANT * size._1
  val impurityShift: Double = IMPURITY_SHIFT_CONSTANT * size._1
  val phShift: Double = PH_SHIFT_CONSTANT * size._1
  def isAlive: Boolean =
    val alive = satiety > 0 && !isFishDeadOfOldAge
    if !alive then model.addChronicleEvent(Events.ENTITY_DEATH(this))
    alive

  private def isFishDeadOfOldAge: Boolean =
    age > DeathProbabilityFish.MIN_AGE_FISH &&
      Random.between(0, DeathProbabilityFish.PROB_AGE) < DeathProbabilityFish.FISH_AGE(age)

  override def equals(that: Any): Boolean =
    that match
      case that: Fish => that.name == name
      case _ => false

  private def calculatePosition(speed: (Double, Double))(mul: Double): (Double, Double) =
    (this.position._1 + speed._1 * mul, this.position._2 + speed._2 * mul)

  override def updateSatiety(newSatiety: Int): Fish =
    this.copy(satiety = newSatiety)

  override def updateReproductionFactor(newReproductionFactor: Int): Fish =
    this.copy(reproductionFactor = newReproductionFactor)

  override def move(mul: Double): Fish =
    var (posX, posY) = calculatePosition(this.speed)(mul)
    var (speedX, speedY) = this.speed

    posX match
      case x if x < 0 =>
        posX = 0
        speedX = speedX * -1
      case x if x + this.size._1 > AquariumDimensions.WIDTH =>
        posX = AquariumDimensions.WIDTH - this.size._1
        speedX = speedX * -1
      case _ =>

    posY match
      case y if y < 0 =>
        posY = 0
        speedY = speedY * -1
      case y if y + this.size._2 > AquariumDimensions.HEIGHT =>
        posY = AquariumDimensions.HEIGHT - this.size._2
        speedY = speedY * -1
      case _ =>

    this.copy(position = (posX, posY), speed = (speedX, speedY))

/** Companion object of the case class */
object Fish:
  var n: Int = 0
  val MAX_HEIGHT: Double = 10
  val MAX_WIDTH: Double = 20
  val MEAT_AMOUNT: Double = 0.6
  val MAX_SATIETY: Int = 100
  val SATIETY_SHIFT: Int = 1
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
  val AGE_FISH: Int = 1
  val OXYGEN_SHIFT_CONSTANT: Double = -Math.pow(10, -3)
  val IMPURITY_SHIFT_CONSTANT: Double = Math.pow(10, -4) * 5
  val PH_SHIFT_CONSTANT: Double = Math.pow(10, -4) * 5
  val MAX_REPRODUCTION_FACTOR: Int = 100
  val REPRODUCTION_FACTOR_SHIFT: Int = 5
  val REPRODUCTION_COST: Int = 50
  val MAX_SPEED: Int = 5
  val MIN_SPEED: Int = 1
  val AGE_SHIFT: Int = 1

  /** Method that increments the int variable in the fish name suffix */
  def getAndIncrementN(): Int =
    n = n + 1
    n
