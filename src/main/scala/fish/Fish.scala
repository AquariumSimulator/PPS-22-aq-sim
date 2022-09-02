package fish

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100
  val MIN_SIZE: Double = 0.5
  val MAX_SIZE: Double = 2.5
  val MEAT_AMOUNT: Int = 10

trait Fish():

  import scala.util.Random
  import fish.Fish.{MAX_HUNGER, n, MIN_SIZE, MAX_SIZE}

  val name: String = "fish-" + n
  n = n + 1

  var hunger: Int = MAX_HUNGER
  val age: Int = 0
  val speed: (Double, Double) = (0.0, 0.0)
  val size: Double = Random.between(MIN_SIZE, MAX_SIZE)

  def isAlive: Boolean = hunger > 0

case class HerbivorousFish() extends Fish:

  import fish.Fish.MAX_HUNGER

  def eat(food: HerbivorousFood): Unit =
    hunger = MAX_HUNGER min hunger + food.NUTRITION_AMOUNT

case class CarnivorousFish() extends Fish:

  import fish.Fish.{MEAT_AMOUNT, MAX_HUNGER}

  def eat(fish: Fish): Unit =
    hunger = MAX_HUNGER min hunger + (MEAT_AMOUNT * fish.size).floor.toInt

  def eat(food: CarnivorousFood): Unit =
    hunger = MAX_HUNGER min hunger + food.NUTRITION_AMOUNT
