package model.fish

import model.aquarium.AquariumDimensions
import model.fish.Fish.{MAX_SATIETY, MEAT_AMOUNT}
import model.food.Food

/** Trait that models methods for the fishes */
trait UpdateFish:
  /** Set the new satiety of the fish
    *
    * @param newSatiety
    *   the new satiety of the fish
    * @return
    *   a new [[Fish]]
    */
  def updateSatiety(newSatiety: Int): Fish

  /** Set the new reproduction factor of the fish
    *
    * @param newReproductionFactor
    *   the new reproduction factor of the fish
    * @return
    *   a new [[Fish]]
    */
  def updateReproductionFactor(newReproductionFactor: Int): Fish

  /** Moves a fish in a new position
    *
    * @param speedMultiplier
    *   the speed multiplier of the fish
    * @return
    *   a new [[Fish]]
    */
  def move(speedMultiplier: Double): Fish
