package aquarium

/** This class represent the current population of the aquarium */
case class Population()

/** Companion object of the case class */
object Population:

  /** Create a new [[Population]] from a given number of species
    * @param herbivorousFishesNumber
    *   number of herbivorous fishes
    * @param carnivorousFishesNumber
    *   number of carnivorous fishes
    * @param algaeNumber
    *   number of algae
    * @return
    *   a new [[Population]]
    */
  def apply(herbivorousFishesNumber: Int, carnivorousFishesNumber: Int, algaeNumber: Int): Population = Population()
