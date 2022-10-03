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
  // def eat(fish: Fish): Fish
  def eat(food: Food): Fish

object UpdateFish:

  def apply(fish: Fish): UpdateFish = UpdateFishImpl(fish)

  private class UpdateFishImpl(fish: Fish) extends UpdateFish:

    private /*override*/ def calculatePosition(speed: (Double, Double)): (Double, Double) =
      (fish.position._1 + speed._1, fish.position._2 + speed._2)

    override def updateSatiety(newSatiety: Int): Fish =
      fish.copy(satiety = newSatiety)

    override def updateReproductionFactor(newReproductionFactor: Int): Fish =
      fish.copy(reproductionFactor = newReproductionFactor)

    // override def updateSpeed(newSpeed: (Double, Double)): Fish =
    //   fish.copy(speed = newSpeed)

    // override def updatePosition(newPosition: (Double, Double)): Fish =
    //   fish.copy(position = newPosition)

    override def move(speedMultiplier: Double): Fish =
      var newSpeed: (Double, Double) = fish.speed
      var newPosition: (Double, Double) = calculatePosition(
        (fish.speed._1 * speedMultiplier, fish.speed._2 * speedMultiplier)
      )

      newPosition._1 match
        case x if x < 0 =>
          newPosition = (0, newPosition._2)
          newSpeed = (newSpeed._1 * -1, newSpeed._2)
        case x if x + fish.size._1 > AquariumDimensions.WIDTH =>
          newPosition = (AquariumDimensions.WIDTH - fish.size._1, newPosition._2)
          newSpeed = (newSpeed._1 * -1, newSpeed._2)
        case _ =>

      newPosition._2 match
        case y if y < 0 =>
          newPosition = (newPosition._1, 0)
          newSpeed = (newSpeed._1, newSpeed._2 * -1)
        case y if y + fish.size._2 > AquariumDimensions.HEIGHT =>
          newPosition = (newPosition._1, AquariumDimensions.HEIGHT - fish.size._2)
          newSpeed = (newSpeed._1, newSpeed._2 * -1)
        case _ =>

      fish.copy(position = newPosition, speed = newSpeed)

    override def eat(food: Food): Fish =
      fish.copy(satiety = MAX_SATIETY min (fish.satiety + food.nutritionAmount))
