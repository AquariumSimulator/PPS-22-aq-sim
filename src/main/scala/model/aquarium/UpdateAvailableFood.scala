package model.aquarium

import model.Food

/** Trait that models methods for modifying he food available inside the aquarium */
trait UpdateAvailableFood:

  /** Add a new food
    * @param addElem
    * @return
    *   a new set of [[Food]]
    */
  def addFood(addElem: Food): Set[Food]

  /** Remove a particular instance of food
    * @param removeElem
    *   that have to be removed
    * @return
    *   a new set of [[Food]]
    */
  def deleteFood(removeElem: Food): Set[Food]

/** Companion object of [[UpdateAvailableFood]] */
object UpdateAvailableFood:

  /** Create a new [[UpdateAvailableFood]] given a set of [[Food]]
    * @param set
    *   set of [[Food]]
    * @return
    *   a new [[UpdateAvailableFood]]
    */
  def apply(set: Set[Food]): UpdateAvailableFood = UpdateAvailableFoodImpl(set)

  /** Hidden implementation of [[UpdateAvailableFood]]
    * @param set
    *   set of [[Food]]
    */
  private class UpdateAvailableFoodImpl(set: Set[Food]) extends UpdateAvailableFood:

    override def addFood(addElem: Food): Set[Food] =
      set + addElem

    override def deleteFood(removeElem: Food): Set[Food] = set.filterNot(elem => elem == removeElem)
