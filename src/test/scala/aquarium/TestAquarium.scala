package aquarium

import model.aquarium.{Aquarium, InitializeAquarium, AquariumState}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[Aquarium]] */
class TestAquarium extends AnyFunSpec:

  private val herbivorousFishesNumber = 3
  private val carnivorousFishesNumber = 4
  private val algaeNumber = 5

  private val aquarium: Aquarium = Aquarium(
    herbivorousFishesNumber = herbivorousFishesNumber,
    carnivorousFishesNumber = carnivorousFishesNumber,
    algaeNumber = algaeNumber
  )

  describe(s"A new ${Aquarium.getClass.getName}") {
    describe("when initialized") {

      describe(s"with a given ${AquariumState.getClass.getName}") {
        it(s"should have temperature equals to ${InitializeAquarium.TEMPERATURE}") {
          assert(aquarium.aquariumState.temperature == InitializeAquarium.TEMPERATURE)
        }

        it(s"should have brightness equals to ${InitializeAquarium.BRIGHTNESS}") {
          assert(aquarium.aquariumState.brightness == InitializeAquarium.BRIGHTNESS)
        }

        it(s"should have ph equals to ${InitializeAquarium.PH}") {
          assert(aquarium.aquariumState.ph == InitializeAquarium.PH)
        }

        it(s"should have impurity equals to ${InitializeAquarium.IMPURITY}") {
          assert(aquarium.aquariumState.impurity == InitializeAquarium.IMPURITY)
        }

        it(s"should have oxygenation equals to ${InitializeAquarium.OXYGENATION}") {
          assert(aquarium.aquariumState.oxygenation == InitializeAquarium.OXYGENATION)
        }
      }

      describe(s"with a given available food set that") {
        it("should be empty") {
          assert(aquarium.availableFood.isEmpty)
        }
      }
    }
  }
