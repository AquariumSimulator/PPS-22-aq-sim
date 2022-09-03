package aquarium

import aquarium.*
import model.CarnivorousFood
import model.fish.{CarnivorousFish, HerbivorousFish}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert
import scala.util.Random

/** Test for Population (example with herbivorous fish seq) */
class TestUpdatePopulation extends AnyFunSpec:

  val herbivorousFishesNumber = 3
  val carnivorousFishesNumber = 4
  val algaeNumber = 5

  val population = Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber)

  val addElementHerbivorous = HerbivorousFish()
  val addElementCarnivorous = CarnivorousFish()

  val updateHerbivorousAdd = UpdateSpecificPopulation(population.herbivorous)
  val updateCarnivorousAdd = UpdateSpecificPopulation(population.carnivorous)

  val newSeqHerbivorousAdd = updateHerbivorousAdd + addElementHerbivorous
  val newSeqCarnivorousAdd = updateCarnivorousAdd + addElementCarnivorous

  describe("A new UpdatePopulation object") {
    describe("with a given seq of elements (seq of herbivorous fish)") {
      describe("once add method is called") {
        describe("return a new Seq that") {
          it("should have size equal to the old size + 1") {
            assert(newSeqHerbivorousAdd.size == population.herbivorous.size + 1)
          }
          it(s"should contain $addElementHerbivorous") {
            assert(newSeqHerbivorousAdd.contains(addElementHerbivorous))
          }
        }
      }
    }
    describe("with a given seq of elements (seq of carnivorous fish)") {
      describe("once add method is called") {
        describe("return a new Seq that") {
          it("should have size equal to the old size + 1") {
            assert(newSeqCarnivorousAdd.size == population.carnivorous.size + 1)
          }
          it(s"should contain $addElementCarnivorous") {
            assert(newSeqCarnivorousAdd.contains(addElementCarnivorous))
          }
        }
      }
    }
  }
