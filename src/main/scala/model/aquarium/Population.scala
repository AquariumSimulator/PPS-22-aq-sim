package model.aquarium

import model.fish.{CarnivorousFish, HerbivorousFish}
import model.{Algae, CarnivorousFood, HerbivorousFood}

import scala.language.postfixOps
import scala.util.Random

/** This class represent the current population of the aquarium */
case class Population(herbivorous: Set[HerbivorousFish], carnivorous: Set[CarnivorousFish], algae: Set[Algae])

/** Companion object of the case class */
object Population:
  /** Create a new [[Population]] from a given number of species
    *
    * @param herbivorousFishesNumber
    *   number of herbivorous fishes
    * @param carnivorousFishesNumber
    *   number of carnivorous fishes
    * @param algaeNumber
    *   number of algae
    * @return
    *   a new [[Population]]
    */
  def apply(herbivorousFishesNumber: Int, carnivorousFishesNumber: Int, algaeNumber: Int): Population =
    /** Method used to create all the algae required so that no two algae are in the same location
      * @param number
      *   of algae required
      * @return
      *   set of algae
      */
    def addAlgae(number: Int): Set[Algae] =
      def _addAlgae(number: Int, set: Set[Algae]): Set[Algae] =
        val newAlgae =
          Algae(Random.between(0, AquariumDimensions.WIDTH), Random.between(Algae.DEFAULT_HEIGHT, Algae.MAX_HEIGHT))
        number match
          case 0 => set
          case _ =>
            set.contains(newAlgae) match
              case true => _addAlgae(number, set)
              case _ => _addAlgae(number - 1, set + newAlgae)

      _addAlgae(number, Set.empty)

    val setHerbivorous = (1 to herbivorousFishesNumber).map(_ => HerbivorousFish()).toSet

    val setCarnivorous = (1 to carnivorousFishesNumber).map(_ => CarnivorousFish()).toSet

    val setAlgae = addAlgae(algaeNumber)

    Population(setHerbivorous, setCarnivorous, setAlgae)

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
