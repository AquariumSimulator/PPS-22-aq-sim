package TestAquarium

import org.scalatest.funspec.AnyFunSpec
import aquarium.*

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

  val aquariumState = AquariumState(temperature, brightness, ph, impurity, oxygenation)
  val updateAquariumState = UpdateAquariumState(aquariumState)

  describe("A UpdateAquariumState") {
    describe("when clean() is called") {
      it("should return a new AquariumState with impurity set to 0") {
        assert(aquariumState.impurity == impurity)
        val newState = updateAquariumState.clean()
        assert(newState.impurity == 0)
      }
    }

    describe("when updateTemperature() is called") {
      it(s"should return a new AquariumState with temperature set to $newTemperature") {
        assert(aquariumState.temperature == temperature)
        val newState = updateAquariumState.updateTemperature(newTemperature)
        assert(newState.temperature == newTemperature)
      }
    }

    describe("when updateBrightness() is called") {
      it(s"should return a new AquariumState with brightness set to $newBrightness") {
        assert(aquariumState.brightness == brightness)
        val newState = updateAquariumState.updateBrightness(newBrightness)
        assert(newState.brightness == newBrightness)
      }
    }

    describe("when updatePh() is called") {
      it(s"should return a new AquariumState with ph set to $newPh") {
        assert(aquariumState.ph == ph)
        val newState = updateAquariumState.updatePh(newPh)
        assert(newState.ph == newPh)
      }
    }

    describe("when updateOxygenation() is called") {
      it(s"should return a new AquariumState with oxygenation set to $newOxygenation") {
        assert(aquariumState.oxygenation == oxygenation)
        val newState = updateAquariumState.updateOxygenation(newOxygenation)
        assert(newState.oxygenation == newOxygenation)
      }
    }
  }
