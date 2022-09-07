package interaction.aquariumAlgae

import model.*
import model.aquarium.AquariumState
import model.interaction.aquariumAlgae.InteractionAquariumOnAlgae
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

class TestInteractionAquariumOnAlgae extends AnyFunSpec:

  private val aquariumStateAlgaeAlive = AquariumState(5, 50, 7, 10, 10)
  private val aquariumStateAlgaeDead = AquariumState(5, 3, 7, 10, 10)

  private val algaeAliveThatCanGrow = Algae(0, Algae.MAX_HEIGHT / 2)
  private val algaeAliveThatCantGrow = Algae(1, Algae.MAX_HEIGHT)
  private val algaeDead = Algae(1)

  private val interactionAquariumAlgaeForAliveAndGrowTest =
    InteractionAquariumOnAlgae(aquariumStateAlgaeAlive, algaeAliveThatCanGrow)
  private val interactionAquariumAlgaeForAliveAndNotGrowTest =
    InteractionAquariumOnAlgae(aquariumStateAlgaeAlive, algaeAliveThatCantGrow)
  private val interactionAquariumAlgaeForDeadTest = InteractionAquariumOnAlgae(aquariumStateAlgaeDead, algaeDead)

  private val expectedGrowth = 5

  describe("An instance of InteractionAquariumOnAlgae") {
    describe("with a given Algae") {
      describe("and a given AquariumState") {
        describe("when update() is called") {
          describe(
            s"if the brightness level of the AquariumState is equal or lower than ${Algae.LOWER_BRIGHTNESS_LEVEL}"
          ) {
            it(" should return an empty option") {
              assert(interactionAquariumAlgaeForDeadTest.update().isEmpty)
            }
          }
          describe(s"if the brightness level of the AquariumState is greater than ${Algae.LOWER_BRIGHTNESS_LEVEL}") {
            it("it should return a new Algae updated") {
              assert(interactionAquariumAlgaeForAliveAndGrowTest.update().isDefined)
              assert(interactionAquariumAlgaeForAliveAndNotGrowTest.update().isDefined)
            }
            describe(s"if the old algae height was lower than ${Algae.MAX_HEIGHT}") {
              it(s"the new algae should have an height equal to the old one plus $expectedGrowth") {
                assert(
                  interactionAquariumAlgaeForAliveAndGrowTest
                    .update()
                    .get
                    .height == algaeAliveThatCanGrow.height + expectedGrowth
                )
              }
            }
            describe(s"if the old algae height was equal to ${Algae.MAX_HEIGHT}") {
              it(s"the new algae should have an height equal to the old one") {
                assert(
                  interactionAquariumAlgaeForAliveAndNotGrowTest.update().get.height == algaeAliveThatCantGrow.height
                )
              }
            }
          }
        }
      }
    }
  }
