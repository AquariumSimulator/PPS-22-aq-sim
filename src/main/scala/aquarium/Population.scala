package aquarium

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
