package interaction

import model.*
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.interaction.Interaction
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

/** Test for the interaction of an algae on the aquarium state */
class TestInteractionAlgaeOnAquarium extends AnyFunSpec with BeforeAndAfterEach:

  private val aquariumState: AquariumState = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateLimits: AquariumState =
    AquariumState(5, 3, AquariumParametersLimits.PH_MIN, 10, AquariumParametersLimits.OXYGENATION_MAX)

  private val algae: Algae = Algae(0, Algae.MAX_HEIGHT / 2)

  private val interactionAlgaeAquarium: Interaction[AquariumState] = Interaction(aquariumState, algae)
  private val interactionAlgaeAquariumLimits: Interaction[AquariumState] =
    Interaction(aquariumStateLimits, algae)

  describe(s"An instance of ${Interaction.getClass.getName}") {
    describe(s"with a given ${AquariumState.getClass.getName}") {
      describe(s"and a given ${Algae.getClass.getName}") {
        describe("when update is called") {
          describe(s"if the new oxygenation level is lower than ${AquariumParametersLimits.OXYGENATION_MAX}") {
            describe(s"and the new ph is upper than ${AquariumParametersLimits.PH_MIN}") {
              describe(s"it should return a new ${AquariumState.getClass.getName} that") {
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
              describe(s"it should return a new ${AquariumState.getClass.getName} that") {
                it(s"should have a new oxygenation level equal to the old one") {
                  assert(interactionAlgaeAquariumLimits.update().oxygenation == aquariumStateLimits.oxygenation)
                }
                it(s"should have a new ph equal to the old one") {
                  assert(interactionAlgaeAquariumLimits.update().ph == aquariumStateLimits.ph)
                }
              }
            }
          }
        }
      }
    }
  }
