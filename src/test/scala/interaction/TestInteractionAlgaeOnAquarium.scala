package interaction

import model.*
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

class TestInteractionAlgaeOnAquarium extends AnyFunSpec:

  private val aquariumState = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateLimitsTest =
    AquariumState(5, 3, AquariumParametersLimits.PH_MIN, 10, AquariumParametersLimits.OXYGENATION_MAX)

  private val algae = Algae(0, Algae.MAX_HEIGHT / 2)

  private val interactionAlgaeAquarium =
    Interaction(aquariumState, algae)
  private val interactionAlgaeAquariumLimitsTest =
    Interaction(aquariumStateLimitsTest, algae)

  describe("An instance of InteractionAlgaeOnAquarium") {
    describe("with a given AquariumState") {
      describe("and a given Algae") {
        describe("when update() is called") {
          describe(s"if the new oxygenation level is lower than ${AquariumParametersLimits.OXYGENATION_MAX}") {
            describe(s"and the new ph is upper than ${AquariumParametersLimits.PH_MIN}") {
              describe("it should return a new AquariumState that") {
                it(
                  s"should have a new oxygenation level equal to the old one plus the oxygenation shift of the algae"
                ) {
                  assert(interactionAlgaeAquarium.update().oxygenation == aquariumState.oxygenation + algae.oxygenShift)
                }
                it(
                  s"should have a new ph equal to the old one minus the ph shift of the algae"
                ) {
                  assert(interactionAlgaeAquarium.update().ph == aquariumState.ph + algae.phShift)
                }
              }
            }
          }
          describe(s"if the new oxygenation level is equal or upper than ${AquariumParametersLimits.OXYGENATION_MAX}") {
            describe(s"and the new ph is equal or lower than ${AquariumParametersLimits.PH_MIN}") {
              describe("it should return a new AquariumState that") {
                it(s"should have a new oxygenation level equal to the old one") {
                  assert(interactionAlgaeAquariumLimitsTest.update().oxygenation == aquariumStateLimitsTest.oxygenation)
                }
                it(s"should have a new ph equal to the old one") {
                  assert(interactionAlgaeAquariumLimitsTest.update().ph == aquariumStateLimitsTest.ph)
                }
              }
            }
          }
        }
      }
    }
  }
