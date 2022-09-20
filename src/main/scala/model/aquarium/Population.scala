package model.aquarium

import model.fish.{FeedingType, Fish}
import model.{Algae, CarnivorousFood, HerbivorousFood}
import model.aquarium.AquariumDimensions

import scala.util.Random

/** This class represent the current population of the aquarium */
case class Population(herbivorous: Set[Fish], carnivorous: Set[Fish], algae: Set[Algae]) extends UpdatePopulation:
  override def addInhabitant[A](newElem: A): Population =
    newElem match
      case f: Fish if f.feedingType == FeedingType.HERBIVOROUS => this.copy(herbivorous = this.herbivorous + f)
      case f: Fish => this.copy(carnivorous = this.carnivorous + f)
      case a: Algae => this.copy(algae = this.algae + a)

  override def removeInhabitant[A](removeElem: A): Population =
    removeElem match
      case f: Fish if f.feedingType == FeedingType.HERBIVOROUS =>
        this.copy(herbivorous = this.herbivorous.filterNot(fish => fish == f))
      case f: Fish => this.copy(carnivorous = this.carnivorous.filterNot(fish => fish == f))
      case a: Algae => this.copy(algae = this.algae.filterNot(algae => algae == a))

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

    val speed: (Double, Double) = (1, 1) // TODO randomica

    val setHerbivorous =
      (1 to herbivorousFishesNumber)
        .map(_ => Fish(feedingType = FeedingType.HERBIVOROUS, speed = speed, position = randomPosition()))
        .toSet

    val setCarnivorous = (1 to carnivorousFishesNumber).map(_ => Fish(speed = speed, position = randomPosition())).toSet

    val setAlgae = addAlgae(algaeNumber)

    Population(setHerbivorous, setCarnivorous, setAlgae)

  private def randomPosition(): (Double, Double) =
    (Random.between(0, AquariumDimensions.WIDTH), Random.between(0, AquariumDimensions.HEIGHT))
