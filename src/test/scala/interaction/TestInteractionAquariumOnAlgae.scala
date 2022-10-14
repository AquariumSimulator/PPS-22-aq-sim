package interaction
import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.chronicle.Events
import model.interaction.Interaction
import mvc.MVC.model
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interaction of the aquarium state on an algae */
class TestInteractionAquariumOnAlgae extends AnyFunSpec:

  private val aquariumStateAlgaeAlive = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateAlgaeDead = AquariumState(5, 0, 7, 10, 10)

  private val algaeGrowth = Algae(0, Algae.MAX_HEIGHT / 2)
  private val algaeNotGrowth = Algae(1, Algae.MAX_HEIGHT)

  private val interactionAlgaeGrowth =
    Interaction(algaeGrowth, aquariumStateAlgaeAlive)
  private val interactionAlgaeNotGrowth =
    Interaction(algaeNotGrowth, aquariumStateAlgaeAlive)
  private val interactionAlgaeDeath = Interaction(algaeGrowth, aquariumStateAlgaeDead)

  private val expectedGrowth = 1

  describe(s"An instance of ${Interaction.getClass.getName}") {
    describe(s"with a given ${Algae.getClass.getName}") {
      describe(s"and a given ${AquariumState.getClass.getName}") {
        describe("when update is called") {
          describe(
            s"if the brightness level of $aquariumStateAlgaeAlive is greater than ${Algae.LOWER_BRIGHTNESS_LEVEL}"
          ) {
            it(s"it should return a new ${Algae.getClass.getName} updated") {
              assert(interactionAlgaeGrowth.update().isDefined)
              assert(interactionAlgaeNotGrowth.update().isDefined)
            }
            describe(s"if the old algae height was lower than ${Algae.MAX_HEIGHT}") {
              it(s"the new algae should have an height equal to the old one plus $expectedGrowth") {
                assert(
                  interactionAlgaeGrowth
                    .update()
                    .get
                    .height == algaeGrowth.height + expectedGrowth
                )
              }
            }
            describe(s"if the old algae height was equal to ${Algae.MAX_HEIGHT}") {
              it(s"the new algae should have an height equal to the old one") {
                assert(
                  interactionAlgaeNotGrowth.update().get.height == algaeNotGrowth.height
                )
              }
            }
          }
          describe(
            s"if the brightness level of $aquariumStateAlgaeDead is lower than ${Algae.LOWER_BRIGHTNESS_LEVEL}"
          ) {
            var updatedAlgae = interactionAlgaeDeath.update()
            while (updatedAlgae.nonEmpty)
              updatedAlgae = interactionAlgaeDeath.update()
            describe(s"if $algaeGrowth is dead") {
              it(s"should add ${Events.ENTITY_DEATH(algaeGrowth)} in the chronicle") {
                assert(model.chronicle.events.contains(Events.ENTITY_DEATH(algaeGrowth)))
              }
            }
          }
        }
      }
    }
  }
