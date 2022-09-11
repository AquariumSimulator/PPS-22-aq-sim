package model.aquarium

import model.{Algae, CarnivorousFood, HerbivorousFood}

import scala.language.postfixOps
import scala.util.Random
import model.fish.{FeedingType, Fish}

/** This class represent the current population of the aquarium */
case class Population(herbivorous: Set[Fish], carnivorous: Set[Fish], algae: Set[Algae])

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

    val setHerbivorous = (1 to herbivorousFishesNumber).map(_ => Fish(feedingType = FeedingType.HERBIVOROUS)).toSet

    val setCarnivorous = (1 to carnivorousFishesNumber).map(_ => Fish()).toSet

    val setAlgae = addAlgae(algaeNumber)

    Population(setHerbivorous, setCarnivorous, setAlgae)
