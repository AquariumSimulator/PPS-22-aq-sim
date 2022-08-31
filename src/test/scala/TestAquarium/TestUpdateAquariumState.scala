package TestAquarium

import aquarium.*
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[UpdateAquariumState]] */
class TestUpdateAquariumState extends AnyFunSpec:

  val temperature = 5
  val newTemperature = 10

  val brightness = 50
  val newBrightness = 60

  val ph = 6.5
  val newPh = 5.5

  val impurity = 10

  val oxygenation = 10
  val newOxygenation = 15

  val herbivorousNumber = 5
  val carnivorousNumber = 5
  val algaeNumber = 5

  val aquariumState = AquariumState(temperature, brightness, ph, impurity, oxygenation)
  val population = Population(herbivorousNumber, carnivorousNumber, algaeNumber)
  val aquarium = Aquarium(aquariumState, population)

  val updateAquariumState = UpdateAquariumState(aquarium)

  describe("A UpdateAquariumState") {
    describe("when clean() is called") {
      it("should return a new AquariumState with impurity set to 0") {
        assert(aquariumState.impurity == impurity)
        val newAquarium = updateAquariumState.clean()
        assert(newAquarium.aquariumState.impurity == 0)
      }
    }

    describe("when updateTemperature() is called") {
      it(s"should return a new AquariumState with temperature set to $newTemperature") {
        assert(aquariumState.temperature == temperature)
        val newAquarium = updateAquariumState.updateTemperature(newTemperature)
        assert(newAquarium.aquariumState.temperature == newTemperature)
      }
    }

    describe("when updateBrightness() is called") {
      it(s"should return a new AquariumState with brightness set to $newBrightness") {
        assert(aquariumState.brightness == brightness)
        val newAquarium = updateAquariumState.updateBrightness(newBrightness)
        assert(newAquarium.aquariumState.brightness == newBrightness)
      }
    }

    describe("when updatePh() is called") {
      it(s"should return a new AquariumState with ph set to $newPh") {
        assert(aquariumState.ph == ph)
        val newAquarium = updateAquariumState.updatePh(newPh)
        assert(newAquarium.aquariumState.ph == newPh)
      }
    }

    describe("when updateOxygenation() is called") {
      it(s"should return a new AquariumState with oxygenation set to $newOxygenation") {
        assert(aquariumState.oxygenation == oxygenation)
        val newAquarium = updateAquariumState.updateOxygenation(newOxygenation)
        assert(newAquarium.aquariumState.oxygenation == newOxygenation)
      }
    }
  }
