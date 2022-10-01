package model.fish

import model.aquarium.AquariumDimensions
import model.fish.Fish.{MAX_SATIETY, MEAT_AMOUNT}
import model.food.Food

trait UpdateFish:

  // def calculatePosition(speed: (Double, Double)): (Double, Double)
  // def updatePosition(newPosition: (Double, Double)): Fish
  // def updateSpeed(newSpeed: (Double, Double)): Fish
  def updateSatiety(newSatiety: Int): Fish
  def updateReproductionFactor(newReproductionFactor: Int): Fish

  def move(speedMultiplier: Double): Fish
  def eat(fish: Fish): Fish
  def eat(food: Food): Fish

object UpdateFish:

  def apply(fish: Fish): UpdateFish = UpdateFishImpl(fish)

  private class UpdateFishImpl(fish: Fish) extends UpdateFish:

    private /*override*/ def calculatePosition(speed: (Double, Double)): (Double, Double) =
      (fish.position._1 + speed._1, fish.position._2 + speed._2)

    override def updateSatiety(newSatiety: Int): Fish =
      Fish(
        name = fish.name,
        reproductionFactor = fish.reproductionFactor,
        position = fish.position,
        speed = fish.speed,
        satiety = newSatiety,
        age = fish.age,
        size = fish.size,
        feedingType = fish.feedingType
      )

    override def updateReproductionFactor(newReproductionFactor: Int): Fish =
      Fish(
        name = fish.name,
        reproductionFactor = newReproductionFactor,
        position = fish.position,
        speed = fish.speed,
        satiety = fish.satiety,
        age = fish.age,
        size = fish.size,
        feedingType = fish.feedingType
      )

    // override def updateSpeed(newSpeed: (Double, Double)): Fish =
    //   fish.copy(speed = newSpeed)

    // override def updatePosition(newPosition: (Double, Double)): Fish =
    //   fish.copy(position = newPosition)

    override def move(speedMultiplier: Double): Fish =
      var newSpeed: (Double, Double) = fish.speed
      var newPosition: (Double, Double) = calculatePosition(
        (fish.speed._1 * speedMultiplier, fish.speed._2 * speedMultiplier)
      )

      // boundary check (x)
      newPosition._1 match
        case x if x < 0 =>
          newPosition = (0, newPosition._2)
          newSpeed = (newSpeed._1 * -1, newSpeed._2)
        case x if x > AquariumDimensions.WIDTH =>
          newPosition = (AquariumDimensions.WIDTH, newPosition._2)
          newSpeed = (newSpeed._1 * -1, newSpeed._2)
        case _ =>

      // boundary check (y)
      newPosition._2 match
        case y if y < 0 =>
          newPosition = (newPosition._1, 0)
          newSpeed = (newSpeed._1, newSpeed._2 * -1)
        case y if y > AquariumDimensions.HEIGHT =>
          newPosition = (newPosition._1, AquariumDimensions.HEIGHT)
          newSpeed = (newSpeed._1, newSpeed._2 * -1)
        case _ =>

      // creating a new fish with the new position and the new speed
      Fish(
        name = fish.name,
        position = newPosition,
        speed = newSpeed,
        satiety = fish.satiety,
        age = fish.age,
        size = fish.size,
        reproductionFactor = fish.reproductionFactor,
        feedingType = fish.feedingType
      )

    override def eat(eatenFish: Fish): Fish =
      Fish(
        name = fish.name,
        position = fish.position,
        speed = fish.speed,
        satiety = MAX_SATIETY min (fish.satiety + (MEAT_AMOUNT * eatenFish.size).floor.toInt),
        age = fish.age,
        size = fish.size,
        reproductionFactor = fish.reproductionFactor,
        feedingType = fish.feedingType
      )

    override def eat(food: Food): Fish =
      Fish(
        name = fish.name,
        position = fish.position,
        speed = fish.speed,
        satiety = MAX_SATIETY min (fish.satiety + food.nutritionAmount),
        age = fish.age,
        size = fish.size,
        reproductionFactor = fish.reproductionFactor,
        feedingType = fish.feedingType
      )
