package aquarium

import aquarium.*
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[Population]] */
class TestPopulation extends AnyFunSpec:

  val herbivorousFishesNumber = 3
  val carnivorousFishesNumber = 4
  val algaeNumber = 5

  val population = Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber)

  describe("A new Population") {

    describe("with an herbivorous Fishes Seq") {
      it(s"should have $herbivorousFishesNumber elements") {
        assert(population.herbivorous.size == herbivorousFishesNumber)
      }
    }

    describe("with an carnivorous Fishes Seq") {
      it(s"should have an carnivorous Fishes Seq of $carnivorousFishesNumber elements") {
        assert(population.carnivorous.size == carnivorousFishesNumber)
      }
    }

    describe("with an algae Fishes Seq") {
      it(s"should have an algae Seq of $algaeNumber elements") {
        assert(population.algae.size == algaeNumber)
      }

      it("all elements should have different positions"){
        population.algae.foreach(a => assert(population.algae.count(e => e == a) == 1))
      }
    }
  }

  //TODO controlla che non ci siano alghe nella stessa posizione
