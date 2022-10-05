package model.aquarium

/** Trait that models methods for adding and removing elements from a population set */
trait UpdatePopulation:

  /** Add a new element in the fish set
    * @param newElem
    *   element that has to be added
    * @tparam A
    *   type of the element
    * @return
    *   a new [[Population]]
    */
  def addInhabitant[A](newElem: A): Population

  /** Remove an element from the the fish set
    * @param removeElem
    *   element that has to be removed
    * @tparam A
    *   type of the element
    * @return
    *   a new [[Population]]
    */
  def removeInhabitant[A](removeElem: A): Population
