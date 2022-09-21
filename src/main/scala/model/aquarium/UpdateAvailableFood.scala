package model.aquarium

import model.food.Food

/** Trait that models methods for modifying he food available inside the aquarium */
trait UpdateAvailableFood:

  /** Add a new food in the right food set
    * @param addElem
    *   element that has to be added
    * @return
    *   a new [[AvailableFood]]
    */
  def addFood(addElem: Food): AvailableFood

  /** Remove an instance of food in the right set
    * @param removeElem
    *   element that has to be removed
    * @return
    *   new [[AvailableFood]]
    */
  def deleteFood(removeElem: Food): AvailableFood
