package aquarium

import model.aquarium.Population
import model.fish.Fish
import model.{Algae, FeedingType}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert
import scala.util.Random

/** Test for Population (example with herbivorous fish set) */
class TestUpdatePopulation extends AnyFunSpec:

  private val herbivorousFishesNumber = 3
  private val carnivorousFishesNumber = 4
  private val algaeNumber = 5
  private val algaeBase = 1.0

  private val population = Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber)

  private val addHerbivorousElement = Fish(feedingType = FeedingType.HERBIVOROUS)
  private val addCarnivorousElement = Fish()
  private val addAlgaeElement = Algae(algaeBase)

  private val newHerbivorousSetForAddTest = population.addInhabitant(addHerbivorousElement)
  private val newHerbivorousSetForRemoveTest = newHerbivorousSetForAddTest.removeInhabitant(addHerbivorousElement)

  private val newCarnivorousSetForAddTest = population.addInhabitant(addCarnivorousElement)
  private val newCarnivorousSetForRemoveTest = newCarnivorousSetForAddTest.removeInhabitant(addCarnivorousElement)

  private val newAlgaeSetForAddTest = population.addInhabitant(addAlgaeElement)
  private val newAlgaeSetForRemoveTest = newAlgaeSetForAddTest.removeInhabitant(addAlgaeElement)

  describe("Considered an herbivorous fish set") {
    describe("once the addInhabitant method is called") {
      describe("it return a new population that have an updated herbivorous set that") {
        it("should have size equal to the old size + 1") {
          assert(newHerbivorousSetForAddTest.herbivorous.size == population.herbivorous.size + 1)
        }
        it(s"and should contain $addHerbivorousElement") {
          assert(newHerbivorousSetForAddTest.herbivorous.contains(addHerbivorousElement))
        }
      }
    }
    describe("once the removeInhabitant method is called") {
      describe("it return a new population that have an updated herbivorous set that") {
        it("should have size equal to the old size") {
          assert(newHerbivorousSetForRemoveTest.herbivorous.size == population.herbivorous.size)
        }
        it(s"and should not contain $addHerbivorousElement") {
          assert(!newHerbivorousSetForRemoveTest.herbivorous.contains(addHerbivorousElement))
        }
      }
    }
  }
  describe("Considered an carnivorous fish set") {
    describe("once the addInhabitant method is called") {
      describe("it return a new population that have an updated carnivorous set that") {
        it("should have size equal to the old size + 1") {
          assert(newCarnivorousSetForAddTest.carnivorous.size == population.carnivorous.size + 1)
        }
        it(s"and should contain $addCarnivorousElement") {
          assert(newCarnivorousSetForAddTest.carnivorous.contains(addCarnivorousElement))
        }
      }
    }
    describe("once the removeInhabitant method is called") {
      describe("it return a new population that have an updated carnivorous set that") {
        it("should have size equal to the old size") {
          assert(newCarnivorousSetForRemoveTest.carnivorous.size == population.carnivorous.size)
        }
        it(s"and should not contain $addCarnivorousElement") {
          assert(!newCarnivorousSetForRemoveTest.carnivorous.contains(addCarnivorousElement))
        }
      }
    }
  }
  describe("Considered an algae set") {
    describe("once the addInhabitant method is called") {
      describe("it return a new population that have an updated algae set that") {
        it("should have size equal to the old size + 1") {
          assert(newAlgaeSetForAddTest.algae.size == population.algae.size + 1)
        }
        it(s"and should contain $addAlgaeElement") {
          assert(newAlgaeSetForAddTest.algae.contains(addAlgaeElement))
        }
      }
    }
    describe("once removeInhabitant method is called") {
      describe("it return a new population that have an updated algae set that") {
        it("should have size equal to the old size") {
          assert(newAlgaeSetForRemoveTest.algae.size == population.algae.size)
        }
        it(s"and should not contain $addAlgaeElement") {
          assert(!newAlgaeSetForRemoveTest.algae.contains(addAlgaeElement))
        }
      }
    }
  }
