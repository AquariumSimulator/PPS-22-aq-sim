package model.aquarium

import model.FeedingType
import model.food.Food

/** Trait that models methods for modifying he food available inside the aquarium */
trait AvailableFood:
  /** Set of the food in the aquarium */
  val availableFood: Set[Food]

  /** Method that
    * @return
    *   the herbivorous food of the aquarium
    */
  def herbivorousFood: Set[Food] =
    selectType(FeedingType.HERBIVOROUS)

  /** Method that
    * @return
    *   the carnivorous food of the aquarium
    */
  def carnivorousFood: Set[Food] =
    selectType(FeedingType.CARNIVOROUS)

  /** Method that give a feeding type return a set containing all the food of that type
    *
    * @param feedingType
    *   of the food that have to be returned
    * @return
    *   set containing all the food of the specified feeding type
    */
  private def selectType(feedingType: FeedingType): Set[Food] =
    availableFood.filter(f => f.feedingType == feedingType)

  /** Given a new [[Food]], it is added to the set of the available food in the aquarium
    * @param food
    *   the food that has to be added
    * @return
    *   the new set of food
    */
  def addFood(food: Food): Set[Food] =
    availableFood + food

  /** Given a [[Food]] instance, it is removed, if present, from the set of the available food in the aquarium
    * @param food
    *   the food that has to be removed
    * @return
    *   the new set of food
    */
  def deleteFood(food: Food): Set[Food] =
    availableFood - food
