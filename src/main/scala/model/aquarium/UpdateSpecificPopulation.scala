package model.aquarium

/** Trait that models methods for adding and removing elements from a population set
  * @tparam A
  *   type of the set that have to be updated
  */
trait UpdateSpecificPopulation[A]:
  /** Add a new element in the set
    * @param newElem
    *   element that has to be added
    * @return
    *   new set of type A with the new element added
    */
  def +(newElem: A): Set[A]

  /** Remove an element from the set
    * @param removeElem
    *   element that has to be removed
    * @return
    *   new set of type A with the element removed
    */
  def -(removeElem: A): Set[A]

/** Companion object of [[UpdateSpecificPopulation]] */
object UpdateSpecificPopulation:

  /** Create a new [[UpdateSpecificPopulation]] by a given set of type A
    * @param set
    *   set that has to be updated
    * @tparam A
    *   type of the set that have to be updated
    * @return
    *   a new [[UpdateSpecificPopulation]]
    */
  def apply[A](set: Set[A]): UpdateSpecificPopulation[A] = UpdateSpecificPopulationImpl[A](set)

  /** Hidden implementation of [[UpdateSpecificPopulation]]
    *
    * @param set
    *   set of [[A]] that have to be updated
    */
  private class UpdateSpecificPopulationImpl[A](set: Set[A]) extends UpdateSpecificPopulation[A]:
    override def +(newElem: A): Set[A] = set + newElem

    override def -(removeElem: A): Set[A] = set.filterNot(elem => elem == removeElem)
