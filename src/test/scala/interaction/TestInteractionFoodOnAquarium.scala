package interaction

import model.*
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.food.Food
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interaction of food on the aquarium state */
class TestInteractionFoodOnAquarium extends AnyFunSpec:
  private val aquariumState = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateLimits =
    AquariumState(
      5,
      50,
      7,
      AquariumParametersLimits.IMPURITY_MAX,
      10
    )

  private val food = Food()

  private val interactionFoodAquarium =
    Interaction(aquariumState, food)
  private val interactionFoodAquariumLimits =
    Interaction(aquariumStateLimits, food)

  describe(s"An instance of ${Interaction.getClass.getName}") {
    describe(s"with a given ${AquariumState.getClass.getName}") {
      describe(s"and a given ${Food.getClass.getName}") {
        describe("when update is called") {
          describe(s"and the new impurity level is lower than ${AquariumParametersLimits.IMPURITY_MAX}") {
            describe(s"it should return a new ${AquariumState.getClass.getName} that") {
              it(
                s"should have a new impurity level equal to the old one plus the impurity shift of the food"
              ) {
                assert(interactionFoodAquarium.update().impurity == aquariumState.impurity + food.impurityShift)
              }
            }
          }

          describe(s"and the new impurity level is equal or upper than ${AquariumParametersLimits.IMPURITY_MAX}") {
            describe(s"it should return a new ${AquariumState.getClass.getName} that") {
              it(s"should have a new impurity level equal to the old one") {
                assert(interactionFoodAquariumLimits.update().impurity == aquariumStateLimits.impurity)
              }
            }
          }
        }
      }
    }
  }
