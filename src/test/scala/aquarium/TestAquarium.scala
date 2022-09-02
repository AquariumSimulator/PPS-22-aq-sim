package aquarium

import aquarium.*
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[Aquarium]] */
class TestAquarium extends AnyFunSpec:

  val herbivorousFishesNumber = 3
  val carnivorousFishesNumber = 4
  val algaeNumber = 5

  val aquarium: Aquarium = Aquarium(
    herbivorousFishesNumber = herbivorousFishesNumber,
    carnivorousFishesNumber = carnivorousFishesNumber,
    algaeNumber = algaeNumber
  )

  describe("A new Aquarium") {
    describe("when initialized") {

      describe("with a given aquariumState") {
        it(s"should have temperature equals to INITIAL_TEMPERATURE") {
          assert(aquarium.aquariumState.temperature == InitializeAquarium.TEMPERATURE)
        }

        it(s"should have brightness equals to INITIAL_BRIGHTNESS") {
          assert(aquarium.aquariumState.brightness == InitializeAquarium.BRIGHTNESS)
        }

        it(s"should have ph equals to INITIAL_PH") {
          assert(aquarium.aquariumState.ph == InitializeAquarium.PH)
        }

        it(s"should have impurity equals to INITIAL_IMPURITY") {
          assert(aquarium.aquariumState.impurity == InitializeAquarium.IMPURITY)
        }

        it(s"should have oxygenation equals to INITIAL_OXYGENATION") {
          assert(aquarium.aquariumState.oxygenation == InitializeAquarium.OXYGENATION)
        }
      }

      describe("with a given availableFood") {
        it("should have herbivorousFood set empty") {
          assert(aquarium.availableFood.herbivorousFood.isEmpty)
        }

        it("should have carnivorousFood set empty") {
          assert(aquarium.availableFood.carnivorousFood.isEmpty)
        }
      }
    }
  }
