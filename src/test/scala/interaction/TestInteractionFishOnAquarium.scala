package interaction

import model._
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interaction of a fish on the aquarium state */
class TestInteractionFishOnAquarium extends AnyFunSpec:
  private val aquariumState = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateLimits =
    AquariumState(
      5,
      3,
      AquariumParametersLimits.PH_MAX,
      AquariumParametersLimits.IMPURITY_MAX,
      AquariumParametersLimits.OXYGENATION_MIN
    )

  private val fish = Fish()

  private val interactionFishAquarium =
    Interaction(aquariumState, fish)
  private val interactionFishAquariumLimits =
    Interaction(aquariumStateLimits, fish)

  describe(s"An instance of ${Interaction.getClass.getName}") {
    describe(s"with a given ${AquariumState.getClass.getName}") {
      describe(s"and a given ${Fish.getClass.getName}") {
        describe("when update is called") {
          describe(s"if the new oxygenation level is lower than ${AquariumParametersLimits.OXYGENATION_MAX}") {
            describe(s"and the new ph is upper than ${AquariumParametersLimits.PH_MIN}") {
              describe(s"and the new impurity level is lower than ${AquariumParametersLimits.IMPURITY_MAX}") {
                describe("it should return a new AquariumState that") {
                  it(
                    s"should have a new oxygenation level equal to the old one minus the oxygenation shift of the fish"
                  ) {
                    assert(interactionFishAquarium.update().oxygenation == aquariumState.oxygenation + fish.oxygenShift)
                  }
                  it(
                    s"should have a new ph equal to the old one plus the ph shift of the fish"
                  ) {
                    assert(interactionFishAquarium.update().ph == aquariumState.ph + fish.phShift)
                  }
                  it(
                    s"should have a new impurity level equal to the old one plus the impurity shift of the fish"
                  ) {
                    assert(interactionFishAquarium.update().impurity == aquariumState.impurity + fish.impurityShift)
                  }
                }
              }
            }
          }
          describe(s"if the new oxygenation level is equal or upper than ${AquariumParametersLimits.OXYGENATION_MAX}") {
            describe(s"and the new ph is equal or lower than ${AquariumParametersLimits.PH_MIN}") {
              describe(s"and the new impurity level is equal or upper than ${AquariumParametersLimits.IMPURITY_MAX}") {
                describe("it should return a new AquariumState that") {
                  it(s"should have a new oxygenation level equal to the old one") {
                    assert(
                      interactionFishAquariumLimits.update().oxygenation == aquariumStateLimits.oxygenation
                    )
                  }
                  it(s"should have a new ph equal to the old one") {
                    assert(interactionFishAquariumLimits.update().ph == aquariumStateLimits.ph)
                  }
                  it(s"should have a new impurity level equal to the old one") {
                    assert(interactionFishAquariumLimits.update().impurity == aquariumStateLimits.impurity)
                  }
                }
              }
            }
          }
        }
      }
    }
  }
