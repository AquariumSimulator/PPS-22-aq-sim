package model.aquarium

import model.Food

/** Trait that models methods for modifying he food available inside the aquarium
  * @tparam A
  *   type of food
  */
trait UpdateAvailableFood:

  /** Add a new food
    * @param addElem
    * @return
    *   a new set of food
    */
  def addFood[A](addElem: A): AvailableFood

  /** Remove a particular instance of food
    * @param removeElem
    *   that have to be removed
    * @return
    *   a new set of food
    */
  def deleteFood[A](removeElem: A): AvailableFood

/*/** Companion object of [[UpdateAvailableFood]] */
object UpdateAvailableFood:

  /** Create a new [[UpdateAvailableFood]] given a set of [[A]]
 * @param set
 *   set of food
 * @return
 *   a new [[UpdateAvailableFood]]
 */
  def apply[A](set: Set[A]): UpdateAvailableFood[A] = UpdateAvailableFoodImpl[A](set)

  /** Hidden implementation of [[UpdateAvailableFood]]
 * @param set
 *   set of food
 */
  private class UpdateAvailableFoodImpl[A](set: Set[A]) extends UpdateAvailableFood[A]:

    override def addFood(addElem: A): Set[A] =
      set + addElem

    override def deleteFood(removeElem: A): Set[A] = set.filterNot(elem => elem == removeElem)*/
