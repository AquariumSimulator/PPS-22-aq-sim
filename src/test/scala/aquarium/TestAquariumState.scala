package aquarium

import model.aquarium.AquariumState
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[AquariumState]] */
class TestAquariumState extends AnyFunSpec:

  val temperature = 5
  val brightness = 50
  val ph = 7
  val impurity = 10
  val oxygenation = 10

  val aquariumState = AquariumState(temperature, brightness, ph, impurity, oxygenation)

  describe("A new AquariumState") {

    describe("with given arguments") {
      it(s"should have temperature equals to $temperature") {
        assert(aquariumState.temperature == temperature)
      }

      it(s"should have brightness equals to $brightness") {
        assert(aquariumState.brightness == brightness)
      }

      it(s"should have ph equals to $ph") {
        assert(aquariumState.ph == ph)
      }

      it(s"should have impurity equals to $impurity") {
        assert(aquariumState.impurity == impurity)
      }

      it(s"should have oxygenation equals to $oxygenation") {
        assert(aquariumState.oxygenation == oxygenation)
      }
    }
  }
