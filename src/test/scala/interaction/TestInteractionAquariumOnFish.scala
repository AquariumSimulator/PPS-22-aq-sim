package interaction

import model.*
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.fish.Fish
import model.interaction.{Interaction, MultiplierVelocityFish}
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interaction of the aquarium state on a fish */
class TestInteractionAquariumOnFish extends AnyFunSpec:

  private val fish = Fish()

  private val aquariumState = AquariumState(5, 50, 7, 10, 10)

  private val interaction = Interaction(fish, aquariumState)

  private val calculateMultiplier = (aquariumState: AquariumState) =>
    MultiplierVelocityFish.SPEED_MULTIPLIER_TEMPERATURE(aquariumState.temperature) * MultiplierVelocityFish
      .SPEED_MULTIPLIER_IMPURITY(aquariumState.impurity)
  private val expectedMultiplier = calculateMultiplier(aquariumState)

  private val newSpeed = (fish.speed._1 * expectedMultiplier, fish.speed._2 * expectedMultiplier)

  describe("An instance of InteractionAquariumOnFish") {
    describe("with a given Fish") {
      describe("and a given AquariumState") {
        describe("when update() is called") {
          it(
            "should return a new Algae fish with its speed updated proportionally to the temperature and the impurity"
          ) {
            var updatedFish = interaction.update()
            while (updatedFish.isEmpty)
              updatedFish = interaction.update()

            assert(updatedFish.get.speed == newSpeed)
          }
        }
      }
    }
  }
