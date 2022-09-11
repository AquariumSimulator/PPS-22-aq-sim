package aquarium

import model.aquarium.{Population, UpdateSpecificPopulation}
import model.{Algae, CarnivorousFood}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert
import scala.util.Random
import model.fish.Fish

/** Test for Population (example with herbivorous fish set) */
class TestUpdatePopulation extends AnyFunSpec:

  val herbivorousFishesNumber = 3
  val carnivorousFishesNumber = 4
  val algaeNumber = 5
  val algaeBase = 1.0

  val population = Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber)

  val addHerbivorousElement = Fish()
  val addCarnivorousElement = Fish()
  val addAlgaeElement = Algae(algaeBase)

  val updateHerbivorousForAddTest = UpdateSpecificPopulation(population.herbivorous)
  val newHerbivorousSetForAddTest = updateHerbivorousForAddTest + addHerbivorousElement
  val updateHerbivorousForRemoveTest = UpdateSpecificPopulation(newHerbivorousSetForAddTest)
  val newHerbivorousSetForRemoveTest = updateHerbivorousForRemoveTest - addHerbivorousElement

  val updateCarnivorousForAddTest = UpdateSpecificPopulation(population.carnivorous)
  val newCarnivorousSetForAddTest = updateCarnivorousForAddTest + addCarnivorousElement
  val updateCarnivorousForRemoveTest = UpdateSpecificPopulation(newCarnivorousSetForAddTest)
  val newCarnivorousSetForRemoveTest = updateCarnivorousForRemoveTest - addCarnivorousElement

  val updateAlgaeForAddTest = UpdateSpecificPopulation(population.algae)
  val newAlgaeSetForAddTest = updateAlgaeForAddTest + addAlgaeElement
  val updateAlgaeForRemoveTest = UpdateSpecificPopulation(newAlgaeSetForAddTest)
  val newAlgaeSetForRemoveTest = updateAlgaeForRemoveTest - addAlgaeElement

  describe("A new UpdatePopulation object") {
    describe("with a given set of elements (set of herbivorous fish)") {
      describe("once add method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size + 1") {
            assert(newHerbivorousSetForAddTest.size == population.herbivorous.size + 1)
          }
          it(s"should contain $addHerbivorousElement") {
            assert(newHerbivorousSetForAddTest.contains(addHerbivorousElement))
          }
        }
      }
      describe("once remove method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size") {
            assert(newHerbivorousSetForRemoveTest.size == population.herbivorous.size)
          }
          it(s"should not contain $addHerbivorousElement") {
            assert(!newHerbivorousSetForRemoveTest.contains(addHerbivorousElement))
          }
        }
      }
    }
    describe("with a given set of elements (set of carnivorous fish)") {
      describe("once add method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size + 1") {
            assert(newCarnivorousSetForAddTest.size == population.carnivorous.size + 1)
          }
          it(s"should contain $addCarnivorousElement") {
            assert(newCarnivorousSetForAddTest.contains(addCarnivorousElement))
          }
        }
      }
      describe("once remove method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size") {
            assert(newCarnivorousSetForRemoveTest.size == population.carnivorous.size)
          }
          it(s"should not contain $addCarnivorousElement") {
            assert(!newCarnivorousSetForRemoveTest.contains(addCarnivorousElement))
          }
        }
      }
    }
    describe("with a given set of elements (set of algae)") {
      describe("once add method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size + 1") {
            assert(newAlgaeSetForAddTest.size == population.algae.size + 1)
          }
          it(s"should contain $addAlgaeElement") {
            assert(newAlgaeSetForAddTest.contains(addAlgaeElement))
          }
        }
      }
      describe("once remove method is called") {
        describe("return a new set that") {
          it("should have size equal to the old size") {
            assert(newAlgaeSetForRemoveTest.size == population.algae.size)
          }
          it(s"should not contain $addAlgaeElement") {
            assert(!newAlgaeSetForRemoveTest.contains(addAlgaeElement))
          }
        }
      }
    }
  }
