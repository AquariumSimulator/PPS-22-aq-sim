package aquarium

import model.aquarium.{Aquarium, InitializeAquarium}
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

      describe("with a given availableFood that") {
        it("should have an empty herbivorous food set") {
          assert(aquarium.availableFood.herbivorousFood.isEmpty)
        }

        it("should have an empty carnivorous food set") {
          assert(aquarium.availableFood.carnivorousFood.isEmpty)
        }
      }
    }
  }
