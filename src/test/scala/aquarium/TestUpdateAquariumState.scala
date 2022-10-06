package aquarium

import model.aquarium.*
import model.aquarium.AquariumParametersLimits.*
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interface [[UpdateAquariumState]] */
class TestUpdateAquariumState extends AnyFunSpec:

  private val temperature = 5
  private val newTemperature = 10
  private val brightness = 50
  private val newBrightness = 60
  private val ph = 6.5
  private val newPh = 5.5
  private val impurity = 10
  private val newImpurity = 0
  private val oxygenation = 10
  private val newOxygenation = 15

  private val aquariumState = AquariumState(temperature, brightness, ph, impurity, oxygenation)
  private val aquariumStateUpperLimit =
    AquariumState(TEMPERATURE_MAX, BRIGHTNESS_MAX, PH_MAX, IMPURITY_MAX, OXYGENATION_MAX)
  private val aquariumStateLowerLimit =
    AquariumState(TEMPERATURE_MIN, BRIGHTNESS_MIN, PH_MIN, IMPURITY_MIN, OXYGENATION_MIN)
  private val delta = 1

  describe("An AquariumState") {
    describe("when updateImpurity() is called") {
      describe(s"if the new value is not lower than $IMPURITY_MIN or greater than $IMPURITY_MAX") {
        it("should return a new AquariumState with impurity equal to the new one") {
          val newAquarium = aquariumState.updateImpurity(newImpurity)
          assert(newAquarium.impurity == newImpurity)
        }
      }
      describe(s"if the new value is lower than $IMPURITY_MIN or greater than $IMPURITY_MAX") {
        it("should return a new AquariumState with impurity equal to the old one") {
          assert(
            aquariumStateUpperLimit
              .updateImpurity(aquariumStateUpperLimit.impurity + delta)
              .impurity === aquariumStateUpperLimit.impurity
          )
          assert(
            aquariumStateLowerLimit
              .updateImpurity(aquariumStateLowerLimit.impurity - delta)
              .impurity === aquariumStateLowerLimit.impurity
          )
        }
      }
    }

    describe("when updateTemperature() is called") {
      describe(s"if the new value is not lower than $TEMPERATURE_MIN or greater than $TEMPERATURE_MAX") {
        it(s"should return a new AquariumState with temperature set to $newTemperature") {
          val newAquarium = aquariumState.updateTemperature(newTemperature)
          assert(newAquarium.temperature == newTemperature)
        }
        describe(s"if the new value is lower than $TEMPERATURE_MIN or greater than $TEMPERATURE_MAX") {
          it("should return a new AquariumState with temperature equal to the old one") {
            assert(
              aquariumStateUpperLimit
                .updateTemperature(aquariumStateUpperLimit.temperature + delta)
                .temperature === aquariumStateUpperLimit.temperature
            )
            assert(
              aquariumStateLowerLimit
                .updateTemperature(aquariumStateLowerLimit.temperature - delta)
                .temperature === aquariumStateLowerLimit.temperature
            )
          }
        }
      }
    }

    describe("when updateBrightness() is called") {
      describe(s"if the new value is not lower than $BRIGHTNESS_MIN or greater than $BRIGHTNESS_MAX") {
        it(s"should return a new AquariumState with brightness set to $newBrightness") {
          val newAquarium = aquariumState.updateBrightness(newBrightness)
          assert(newAquarium.brightness == newBrightness)
        }
        describe(s"if the new value is lower than $BRIGHTNESS_MIN or greater than $BRIGHTNESS_MAX") {
          it("should return a new AquariumState with brightness equal to the old one") {
            assert(
              aquariumStateUpperLimit
                .updateBrightness(aquariumStateUpperLimit.brightness + delta)
                .brightness === aquariumStateUpperLimit.brightness
            )
            assert(
              aquariumStateLowerLimit
                .updateBrightness(aquariumStateLowerLimit.brightness - delta)
                .brightness === aquariumStateLowerLimit.brightness
            )
          }
        }
      }
    }

    describe("when updatePh() is called") {
      describe(s"if the new value is not lower than $PH_MIN or greater than $PH_MAX") {
        it(s"should return a new AquariumState with ph set to $newPh") {
          val newAquarium = aquariumState.updatePh(newPh)
          assert(newAquarium.ph == newPh)
        }
        describe(s"if the new value is lower than $PH_MIN or greater than $PH_MAX") {
          it("should return a new AquariumState with ph equal to the old one") {
            assert(
              aquariumStateUpperLimit
                .updatePh(aquariumStateUpperLimit.ph + delta)
                .ph === aquariumStateUpperLimit.ph
            )
            assert(
              aquariumStateLowerLimit
                .updatePh(aquariumStateLowerLimit.ph - delta)
                .ph === aquariumStateLowerLimit.ph
            )
          }
        }
      }
    }

    describe("when updateOxygenation() is called") {
      describe(s"if the new value is not lower than $OXYGENATION_MIN or greater than $OXYGENATION_MAX") {
        it(s"should return a new AquariumState with oxygenation set to $newOxygenation") {
          val newAquarium = aquariumState.updateOxygenation(newOxygenation)
          assert(newAquarium.oxygenation == newOxygenation)
        }
        describe(s"if the new value is lower than $OXYGENATION_MIN or greater than $OXYGENATION_MAX") {
          it("should return a new AquariumState with oxygenation equal to the old one") {
            assert(
              aquariumStateUpperLimit
                .updateOxygenation(aquariumStateUpperLimit.oxygenation + delta)
                .oxygenation === aquariumStateUpperLimit.oxygenation
            )
            assert(
              aquariumStateLowerLimit
                .updateOxygenation(aquariumStateLowerLimit.oxygenation - delta)
                .oxygenation === aquariumStateLowerLimit.oxygenation
            )
          }
        }
      }
    }
  }
