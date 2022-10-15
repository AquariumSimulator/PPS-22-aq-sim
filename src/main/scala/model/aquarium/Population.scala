package model.aquarium

import model.aquarium.AquariumDimensions
import model.aquarium.AquariumParametersLimits._
import model.fish.Fish
import model.{Algae, Entity, FeedingType}

import scala.annotation.tailrec
import scala.language.postfixOps
import scala.util.Random
import model.chronicle.Events
import mvc.MVC.model

/** Trait that models the population of herbivorous fish, carnivorous fish and algae of the aquarium */
trait FishTypes:

  /** Fish of the aquarium */
  val fish: Set[Fish]

  /** Method that
    * @return
    *   the herbivorous fish of the aquarium
    */
  def herbivorous: Set[Fish] =
    selectType(FeedingType.HERBIVOROUS)

  /** Method that
    * @return
    *   the carnivorous fish of the aquarium
    */
  def carnivorous: Set[Fish] =
    selectType(FeedingType.CARNIVOROUS)

  private def selectType(feedingType: FeedingType): Set[Fish] =
    fish.filter(f => f.feedingType == feedingType)

/** This class represent the current population of the aquarium
  * @param fish
  *   set of all the fish of the aquarium
  * @param algae
  *   set of all the algae of the aquarium
  */
case class Population(override val fish: Set[Fish], algae: Set[Algae]) extends FishTypes with UpdatePopulation:
  override def addInhabitant[A](newInhabitant: A): Population =
    val currentFishNumber: Int = this.fish.size
    newInhabitant match
      case f: Fish if currentFishNumber < FISH_MAX =>
        model.addChronicleEvent(Events.ADDED_ENTITY(f))
        this.copy(fish = this.fish + f)
      case a: Algae if this.algae.size < ALGAE_MAX =>
        model.addChronicleEvent(Events.ADDED_ENTITY(a))
        this.copy(algae = this.algae + a)
      case _ => this

  override def removeInhabitant[A](removedInhabitant: A): Population =
    removedInhabitant match
      case f: Fish =>
        this.copy(fish = this.fish.filterNot(fish => fish == f))
      case a: Algae =>
        this.copy(algae = this.algae.filterNot(algae => algae == a))
      case _ => this

/** Companion object of the case class [[Population]] */
object Population:

  /** Create a new [[Population]] from a given number of species
    *
    * @param herbivorousFishNumber
    *   number of herbivorous fishes
    * @param carnivorousFishNumber
    *   number of carnivorous fishes
    * @param algaeNumber
    *   number of algae
    * @return
    *   a new [[Population]]
    */
  def apply(herbivorousFishNumber: Int, carnivorousFishNumber: Int, algaeNumber: Int): Population =
    /** Method used to create all the algae required so that no two algae are in the same location
      * @param number
      *   of algae required
      * @return
      *   set of algae
      */
    def addAlgae(number: Int): Set[Algae] =
      @tailrec
      def _addAlgae(number: Int, set: Set[Algae]): Set[Algae] =
        val newAlgae =
          Algae(base = randomBase())
        number match
          case 0 => set
          case _ =>
            set.contains(newAlgae) match
              case true => _addAlgae(number, set)
              case _ => _addAlgae(number - 1, set + newAlgae)

      _addAlgae(number, Set.empty)

    Population(
      (1 to herbivorousFishNumber)
        .map(_ => Fish(feedingType = FeedingType.HERBIVOROUS, speed = randomSpeed(), position = randomPosition()))
        .concat((1 to carnivorousFishNumber).map(_ => Fish(speed = randomSpeed(), position = randomPosition())))
        .toSet,
      addAlgae(algaeNumber)
    )

  /** Calculates a random position for a fish */
  def randomPosition(): (Double, Double) =
    (Random.between(0, AquariumDimensions.WIDTH), Random.between(0, AquariumDimensions.HEIGHT))

  /** Calculates a random speed for a fish */
  def randomSpeed(): (Double, Double) =
    (Random.between(Fish.MIN_SPEED, Fish.MAX_SPEED), Random.between(Fish.MIN_SPEED, Fish.MAX_SPEED))

  /** Calculates a random base for an algae */
  def randomBase(): Double =
    0 + (AquariumDimensions.WIDTH - 0) * Random.nextDouble
