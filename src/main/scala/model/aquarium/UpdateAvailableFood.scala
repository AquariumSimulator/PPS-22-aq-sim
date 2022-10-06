package model.aquarium

import model.food.Food

trait UpdateAvailableFood:

  /** Given a new [[Food]], it is added to the set of the available food in the aquarium
    *
    * @param food
    *   the food that has to be added
    * @return
    *   the new aquarium
    */
  def addFood(food: Food): Aquarium

  /** Given a [[Food]] instance, it is removed, if present, from the set of the available food in the aquarium
    *
    * @param food
    *   the food that has to be removed
    * @return
    *   the new aquarium
    */
  def deleteFood(food: Food): Aquarium
