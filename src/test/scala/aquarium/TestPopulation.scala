package aquarium

import model.FeedingType
import model.aquarium.Population
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[Population]] */
class TestPopulation extends AnyFunSpec:

  private val herbivorousFishNumber = 3
  private val carnivorousFishNumber = 4
  private val algaeNumber = 5

  private val population = Population(herbivorousFishNumber, carnivorousFishNumber, algaeNumber)

  describe(s"A new ${Population.getClass.getName}") {

    describe("with a fish set") {
      it(s"should have $herbivorousFishNumber ${FeedingType.HERBIVOROUS} fish") {
        assert(population.herbivorous.size == herbivorousFishNumber)
      }

      it(s"should have $carnivorousFishNumber ${FeedingType.CARNIVOROUS} fish") {
        assert(population.carnivorous.size == carnivorousFishNumber)
      }
    }

    describe("with an algae set") {
      it(s"should have $algaeNumber algae") {
        assert(population.algae.size == algaeNumber)
      }

      it(" all elements should have different positions") {
        population.algae.foreach(a => assert(population.algae.count(e => e == a) == 1))
      }
    }
  }
