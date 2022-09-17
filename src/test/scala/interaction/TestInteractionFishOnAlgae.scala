package interaction

import org.scalatest.funspec.AnyFunSpec
import model.fish.{FeedingType, Fish}
import model.Algae
import model.interaction.Interaction

/** Test for the interaction of the fish on a algae */
class TestInteractionFishOnAlgae extends AnyFunSpec:

  private val fish = Fish(
    feedingType = FeedingType.HERBIVOROUS,
    hunger = 50,
  )
  private val algae = Algae(
    base = 20,
    height = 10,
  )

  private val interaction = Interaction(fish, algae)

  describe("An herbivorous fish") {
    it("should eat an algae when he is hungry") {
      val updatedFish = interaction.update()
      assert(updatedFish.hunger == 60)
    }

    it("shouldn't eat an algae when he isn't hungry") {
      val newFish = Fish(
        feedingType = FeedingType.HERBIVOROUS,
        hunger = 95,
      )
      val newInteraction = Interaction(newFish, algae)
      val updatedFish = newInteraction.update()
      assert(updatedFish.hunger == 95)
    }
  }
