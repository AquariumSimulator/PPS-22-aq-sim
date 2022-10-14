package aquarium

import model.aquarium.Population
import model.fish.Fish
import model.{Algae, FeedingType}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert
import scala.util.Random

/** Test for the interface [[UpdatePopulation]] */
class TestUpdatePopulation extends AnyFunSpec:

  private val herbivorousFishNumber = 3
  private val carnivorousFishNumber = 4
  private val algaeNumber = 5

  private val population = Population(herbivorousFishNumber, carnivorousFishNumber, algaeNumber)

  private val herbivorousFish = Fish(feedingType = FeedingType.HERBIVOROUS)
  private val carnivorousFish = Fish()
  private val algae = Algae(1.0)

  private val populationWithHerbivorousFish = population.addInhabitant(herbivorousFish)
  private val PopulationWithoutHerbivorousFish = populationWithHerbivorousFish.removeInhabitant(herbivorousFish)

  private val populationWithCarnivorousFish = population.addInhabitant(carnivorousFish)
  private val populationWithoutCarnivorousFish = populationWithCarnivorousFish.removeInhabitant(carnivorousFish)

  private val populationWithAlgae = population.addInhabitant(algae)
  private val populationWithoutAlgae = populationWithAlgae.removeInhabitant(algae)

  describe(s"Considered ${Population.getClass.getName}") {
    describe("once the addInhabitant method is called") {
      describe(s"to add an ${FeedingType.HERBIVOROUS} fish") {
        describe(s"it return a new ${Population.getClass.getName} that have an updated fish set that") {
          it("should have size equal to the old size + 1") {
            assert(populationWithHerbivorousFish.herbivorous.size == population.herbivorous.size + 1)
          }
          it(s"and should contain $herbivorousFish") {
            assert(populationWithHerbivorousFish.herbivorous.contains(herbivorousFish))
          }
        }
      }
      describe(s"to add an ${FeedingType.CARNIVOROUS} fish") {
        describe("it return a new population that have an updated carnivorous set that") {
          it("should have size equal to the old size + 1") {
            assert(populationWithCarnivorousFish.carnivorous.size == population.carnivorous.size + 1)
          }
          it(s"and should contain $carnivorousFish") {
            assert(populationWithCarnivorousFish.carnivorous.contains(carnivorousFish))
          }
        }
      }
      describe(s"to add an ${Algae.getClass.getName}") {
        describe(s"it return a new ${Population.getClass.getName} that have an updated algae set that") {
          it("should have size equal to the old size + 1") {
            assert(populationWithAlgae.algae.size == population.algae.size + 1)
          }
          it(s"and should contain $algae") {
            assert(populationWithAlgae.algae.contains(algae))
          }
        }
      }
    }
    describe("once the removeInhabitant method is called") {
      describe(s"to remove a ${FeedingType.HERBIVOROUS} fish") {
        describe(s"it return a new ${Population.getClass.getName} that have an updated fish set that") {
          it("should have size equal to the old size") {
            assert(PopulationWithoutHerbivorousFish.herbivorous.size == population.herbivorous.size)
          }
          it(s"and should not contain $herbivorousFish") {
            assert(!PopulationWithoutHerbivorousFish.herbivorous.contains(herbivorousFish))
          }
        }
      }
      describe(s"to remove a ${FeedingType.CARNIVOROUS} fish") {
        describe(s"it return a new ${Population.getClass.getName} that have an updated carnivorous set that") {
          it("should have size equal to the old size") {
            assert(populationWithoutCarnivorousFish.carnivorous.size == population.carnivorous.size)
          }
          it(s"and should not contain $carnivorousFish") {
            assert(!populationWithoutCarnivorousFish.carnivorous.contains(carnivorousFish))
          }
        }
      }
      describe(s"to remove a ${Algae.getClass.getName} fish") {
        describe(s"it return a new ${Population.getClass.getName} that have an updated algae set that") {
          it("should have size equal to the old size") {
            assert(populationWithoutAlgae.algae.size == population.algae.size)
          }
          it(s"and should not contain $algae") {
            assert(!populationWithoutAlgae.algae.contains(algae))
          }
        }
      }
    }
  }
