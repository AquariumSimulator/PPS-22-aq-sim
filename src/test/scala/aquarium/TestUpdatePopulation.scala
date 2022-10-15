package aquarium

import model.aquarium.{AquariumParametersLimits, Population}
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
  private val populationLimits = Population(
    AquariumParametersLimits.FISH_MAX / 2,
    AquariumParametersLimits.FISH_MAX / 2,
    AquariumParametersLimits.ALGAE_MAX
  )

  private val herbivorousFish = Fish(feedingType = FeedingType.HERBIVOROUS)
  private val carnivorousFish = Fish()
  private val algae = Algae(1.0)

  private val populationWithHerbivorousFish = population.addInhabitant(herbivorousFish)
  private val PopulationWithoutHerbivorousFish = populationWithHerbivorousFish.removeInhabitant(herbivorousFish)

  private val populationWithCarnivorousFish = population.addInhabitant(carnivorousFish)
  private val populationWithoutCarnivorousFish = populationWithCarnivorousFish.removeInhabitant(carnivorousFish)

  private val populationWithAlgae = population.addInhabitant(algae)
  private val populationWithoutAlgae = populationWithAlgae.removeInhabitant(algae)

  private val populationLimitsHerbivorous = populationLimits.addInhabitant(herbivorousFish)
  private val populationLimitsCarnivorous = populationLimits.addInhabitant(carnivorousFish)
  private val populationLimitsAlgae = populationLimits.addInhabitant(algae)

  describe(s"Considered ${Population.getClass.getName}") {
    describe("once the addInhabitant method is called") {
      describe(s"if the fish number is lower then ${AquariumParametersLimits.FISH_MAX}") {
        describe(s"to add an ${FeedingType.HERBIVOROUS} fish") {
          describe(s"it return a new ${Population.getClass.getName} that has an updated fish set that") {
            it("should have size equal to the old size + 1") {
              assert(populationWithHerbivorousFish.herbivorous.size == population.herbivorous.size + 1)
            }
            it(s"and should contain $herbivorousFish") {
              assert(populationWithHerbivorousFish.herbivorous.contains(herbivorousFish))
            }
          }
        }
        describe(s"to add an ${FeedingType.CARNIVOROUS} fish") {
          describe(s"it return a new ${Population.getClass.getName} that has an updated carnivorous set that") {
            it("should have size equal to the old size + 1") {
              assert(populationWithCarnivorousFish.carnivorous.size == population.carnivorous.size + 1)
            }
            it(s"and should contain $carnivorousFish") {
              assert(populationWithCarnivorousFish.carnivorous.contains(carnivorousFish))
            }
          }
        }
      }
      describe(s"if the fish number is equal or greater then ${AquariumParametersLimits.FISH_MAX}") {
        describe(s"to add an ${FeedingType.HERBIVOROUS} fish") {
          it(s"it return a new ${Population.getClass.getName} that has the same fish set") {
            assert(populationLimitsHerbivorous.herbivorous == populationLimits.herbivorous)
          }
        }
        describe(s"to add an ${FeedingType.CARNIVOROUS} fish") {
          it(s"it return a new ${Population.getClass.getName} that has the same carnivorous set") {
            assert(populationLimitsCarnivorous.carnivorous == populationLimits.carnivorous)
          }
        }
      }
      describe(s"if the algae number is lower then ${AquariumParametersLimits.ALGAE_MAX}") {
        describe(s"to add an ${Algae.getClass.getName}") {
          describe(s"it return a new ${Population.getClass.getName} that has an updated algae set that") {
            it("should have size equal to the old size + 1") {
              assert(populationWithAlgae.algae.size == population.algae.size + 1)
            }
            it(s"and should contain $algae") {
              assert(populationWithAlgae.algae.contains(algae))
            }
          }
        }
      }
      describe(s"if the fish number is equal or greater then ${AquariumParametersLimits.ALGAE_MAX}") {
        describe(s"to add an ${Algae.getClass.getName}") {
          it(s"it return a new ${Population.getClass.getName} that has the same algae set ") {
            assert(populationLimitsAlgae.algae == populationLimits.algae)
          }
        }
      }
    }
    describe("once the removeInhabitant method is called") {
      describe(s"to remove a ${FeedingType.HERBIVOROUS} fish") {
        describe(s"it return a new ${Population.getClass.getName} that has an updated fish set that") {
          it("should have size equal to the old size") {
            assert(PopulationWithoutHerbivorousFish.herbivorous.size == population.herbivorous.size)
          }
          it(s"and should not contain $herbivorousFish") {
            assert(!PopulationWithoutHerbivorousFish.herbivorous.contains(herbivorousFish))
          }
        }
      }
      describe(s"to remove a ${FeedingType.CARNIVOROUS} fish") {
        describe(s"it return a new ${Population.getClass.getName} that has an updated carnivorous set that") {
          it("should have size equal to the old size") {
            assert(populationWithoutCarnivorousFish.carnivorous.size == population.carnivorous.size)
          }
          it(s"and should not contain $carnivorousFish") {
            assert(!populationWithoutCarnivorousFish.carnivorous.contains(carnivorousFish))
          }
        }
      }
      describe(s"to remove a ${Algae.getClass.getName} fish") {
        describe(s"it return a new ${Population.getClass.getName} that has an updated algae set that") {
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
