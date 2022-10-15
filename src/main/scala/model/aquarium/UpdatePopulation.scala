package model.aquarium

/** Trait that models methods for adding and removing elements from the population of the aquarium */
trait UpdatePopulation:

  /** Adds a new inhabitant in the aquarium if it's not already full
    * @param newInhabitant
    *   element that has to be added
    * @tparam A
    *   type of the element
    * @return
    *   a new [[Population]]
    */
  def addInhabitant[A](newInhabitant: A): Population

  /** Removes an inhabitant in the aquarium if it's not already full
    * @param removedInhabitant
    *   element that has to be removed
    * @tparam A
    *   type of the element
    * @return
    *   a new [[Population]]
    */
  def removeInhabitant[A](removedInhabitant: A): Population
