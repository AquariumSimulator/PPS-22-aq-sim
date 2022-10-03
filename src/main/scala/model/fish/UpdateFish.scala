package model.fish

import model.aquarium.AquariumDimensions
import model.fish.Fish.{MAX_SATIETY, MEAT_AMOUNT}
import model.food.Food

trait UpdateFish:
  def updateSatiety(newSatiety: Int): Fish
  def updateReproductionFactor(newReproductionFactor: Int): Fish
  def move(speedMultiplier: Double): Fish
  def eat(food: Food): Fish
