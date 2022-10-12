package interaction

import model._
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.food.Food
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interaction of food on the aquarium state */
class TestInteractionFoodOnAquarium extends AnyFunSpec:
  private val aquariumState = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateLimitsTest =
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
  private val interactionFoodAquariumLimitsTest =
    Interaction(aquariumStateLimitsTest, food)

  describe("An instance of InteractionFoodOnAquarium") {
    describe("with a given AquariumState") {
      describe("and a given Food") {
        describe("when update() is called") {
          describe(s"and the new impurity level is lower than ${AquariumParametersLimits.IMPURITY_MAX}") {
            describe("it should return a new AquariumState that") {
              it(
                s"should have a new impurity level equal to the old one plus the impurity shift of the food"
              ) {
                assert(interactionFoodAquarium.update().impurity == aquariumState.impurity + food.impurityShift)
              }
            }
          }

          describe(s"and the new impurity level is equal or upper than ${AquariumParametersLimits.IMPURITY_MAX}") {
            describe("it should return a new AquariumState that") {
              it(s"should have a new impurity level equal to the old one") {
                assert(interactionFoodAquariumLimitsTest.update().impurity == aquariumStateLimitsTest.impurity)
              }
            }
          }
        }
      }
    }
  }
