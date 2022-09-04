package model.aquarium

import model.Algae
import model.fish.{CarnivorousFish, HerbivorousFish}

import scala.language.postfixOps
import scala.util.Random

/** This class represent the current population of the aquarium */
case class Population(herbivorous: Seq[HerbivorousFish], carnivorous: Seq[CarnivorousFish], algae: Seq[Algae])

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
      *   seq of algae
      */
    def addAlgae(number: Int): Seq[Algae] =
      def _addAlgae(number: Int, seq: Seq[Algae]): Seq[Algae] =
        val newAlgae =
          new Algae(Random.between(0, AquariumDimensions.WIDTH), Random.between(Algae.DEFAULT_HEIGHT, Algae.MAX_HEIGHT))
        number match
          case 0 => seq
          case _ =>
            seq.contains(newAlgae) match
              case true => _addAlgae(number, seq)
              case _ => _addAlgae(number - 1, seq :+ newAlgae)

      _addAlgae(number, Seq.empty)

    val seqHerbivorous = (1 to herbivorousFishesNumber).map(_ => HerbivorousFish())

    val seqCarnivorous = (1 to carnivorousFishesNumber).map(_ => CarnivorousFish())

    val seqAlgae = addAlgae(algaeNumber)

    Population(seqHerbivorous, seqCarnivorous, seqAlgae)

/** Trait that models methods for adding and removing elements from a population seq
  * @tparam A
  *   type of the seq that have to be updated
  */
trait UpdateSpecificPopulation[A]:
  /** Add a new element in the seq
    * @param newElem
    *   element that has to be added
    * @return
    *   new Seq of type A with the new element added
    */
  def +(newElem: A): Seq[A]

  /** Remove an element from the seq
    * @param removeElem
    *   element that has to be removed
    * @return
    *   new Seq of type A with the element removed
    */
  def -(removeElem: A): Seq[A]

/** Companion object of [[UpdateSpecificPopulation]] */
object UpdateSpecificPopulation:

  /** Create a new [[UpdateSpecificPopulation]] by a given Seq of type A
    * @param seq
    *   seq that has to be updated
    * @tparam A
    *   type of the seq that have to be updated
    * @return
    *   a new [[UpdateSpecificPopulation]]
    */
  def apply[A](seq: Seq[A]): UpdateSpecificPopulation[A] = UpdateSpecificPopulationImpl[A](seq)

  /** Hidden implementation of [[UpdateSpecificPopulation]]
    *
    * @param seq
    *   seq of [[A]] that have to be updated
    */
  private class UpdateSpecificPopulationImpl[A](seq: Seq[A]) extends UpdateSpecificPopulation[A]:
    override def +(newElem: A): Seq[A] = seq :+ newElem

    override def -(removeElem: A): Seq[A] = ???
