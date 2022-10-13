package interaction

import model.aquarium.{AquariumParametersLimits, AquariumState, Population}
import model.chronicle.Events
import model.fish.Fish
import model.interaction.{Interaction, MultiplierVelocityFish}
import mvc.MVC.model
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interaction of the aquarium state on a fish */
class TestInteractionAquariumOnFish extends AnyFunSpec:

  private val fish = Fish(speed = Population.randomSpeed())

  private val aquariumState = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateLimits =
    aquariumState.copy(oxygenation = AquariumParametersLimits.OXYGENATION_MAX, ph = AquariumParametersLimits.PH_MAX)

  describe(s"An instance of ${Interaction.getClass.getName}") {
    describe(s"with a given ${Fish.getClass.getName}") {
      describe(s"and a given ${AquariumState.getClass.getName}") {
        describe("when update is called") {
          describe(s"if $fish is not dead") {
            var updatedFish = Interaction(fish, aquariumState).update()
            while (updatedFish.isEmpty)
              updatedFish = Interaction(fish, aquariumState).update()
            it(
              s"should return an optional of the same ${Fish.getClass.getName} "
            )(assert(updatedFish.get === fish))
          }
          describe(s"if $fish is dead") {
            var updatedFish = Interaction(fish, aquariumStateLimits).update()
            while (updatedFish.nonEmpty)
              updatedFish = Interaction(fish, aquariumStateLimits).update()
            it(s"should add ${Events.ENTITY_DEATH(fish)} in the chronicle") {
              assert(model.chronicle.events.contains(Events.ENTITY_DEATH(fish)))
            }
          }
        }
      }
    }
  }
