package aquarium

import model.aquarium.{Population, UpdateSpecificPopulation}
import model.fish.{CarnivorousFish, HerbivorousFish}
import model.{Algae, CarnivorousFood}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert
import scala.util.Random

/** Test for Population (example with herbivorous fish seq) */
class TestUpdatePopulation extends AnyFunSpec:

  val herbivorousFishesNumber = 3
  val carnivorousFishesNumber = 4
  val algaeNumber = 5
  val algaeBase = 1.0

  val population = Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber)

  val addElementHerbivorous = HerbivorousFish()
  val addElementCarnivorous = CarnivorousFish()
  val addElementAlgae = Algae(algaeBase)

  val updateHerbivorousAdd = UpdateSpecificPopulation(population.herbivorous)
  val newSeqHerbivorousAdd = updateHerbivorousAdd + addElementHerbivorous
  val updateHerbivorousRemove = UpdateSpecificPopulation(newSeqHerbivorousAdd)
  val newSetHerbivorousRemove = updateHerbivorousRemove - addElementHerbivorous

  val updateCarnivorousAdd = UpdateSpecificPopulation(population.carnivorous)
  val newSeqCarnivorousAdd = updateCarnivorousAdd + addElementCarnivorous
  val updateCarnivorousRemove = UpdateSpecificPopulation(newSeqCarnivorousAdd)
  val newSetCarnivorousRemove = updateCarnivorousRemove - addElementCarnivorous

  val updateAlgaeAdd = UpdateSpecificPopulation(population.algae)
  val newSeqAlgaeAdd = updateAlgaeAdd + addElementAlgae
  val updateAlgaeRemove = UpdateSpecificPopulation(newSeqAlgaeAdd)
  val newSetAlgaeRemove = updateAlgaeRemove - addElementAlgae

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
      describe("once remove method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size") {
            assert(newSetHerbivorousRemove.size == population.herbivorous.size)
          }
          it(s"should not contain $addElementHerbivorous") {
            assert(!newSetHerbivorousRemove.contains(addElementHerbivorous))
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
      describe("once remove method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size") {
            assert(newSetCarnivorousRemove.size == population.carnivorous.size)
          }
          it(s"should not contain $addElementCarnivorous") {
            assert(!newSetCarnivorousRemove.contains(addElementCarnivorous))
          }
        }
      }
    }
    describe("with a given seq of elements (seq of algae)") {
      describe("once add method is called") {
        describe("return a new Seq that") {
          it("should have size equal to the old size + 1") {
            assert(newSeqAlgaeAdd.size == population.algae.size + 1)
          }
          it(s"should contain $addElementAlgae") {
            assert(newSeqAlgaeAdd.contains(addElementAlgae))
          }
        }
      }
      describe("once remove method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size") {
            assert(newSetAlgaeRemove.size == population.algae.size)
          }
          it(s"should not contain $addElementAlgae") {
            assert(!newSetAlgaeRemove.contains(addElementAlgae))
          }
        }
      }
    }
  }
