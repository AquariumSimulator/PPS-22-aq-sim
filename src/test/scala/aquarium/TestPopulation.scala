package aquarium

import model.aquarium.Population
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[Population]] */
class TestPopulation extends AnyFunSpec:

  private val herbivorousFishesNumber = 3
  private val carnivorousFishesNumber = 4
  private val algaeNumber = 5

  private val population = Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber)

  describe("A new Population") {

    describe("with an herbivorous Fishes Set") {
      it(s"should have $herbivorousFishesNumber elements") {
        assert(population.herbivorous.size == herbivorousFishesNumber)
      }
    }

    describe("with an carnivorous Fishes Set") {
      it(s"should have an carnivorous Fishes Set of $carnivorousFishesNumber elements") {
        assert(population.carnivorous.size == carnivorousFishesNumber)
      }
    }

    describe("with an algae Fishes Set") {
      it(s"should have an algae Set of $algaeNumber elements") {
        assert(population.algae.size == algaeNumber)
      }

      it("all elements should have different positions") {
        population.algae.foreach(a => assert(population.algae.count(e => e == a) == 1))
      }
    }
  }
