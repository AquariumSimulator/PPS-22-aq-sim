package interaction

import model.{Algae, FeedingType}
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

/** Test for the interaction of the fish on a algae */
class TestInteractionFishOnAlgae extends AnyFunSpec:

  private val fish = Fish(
    feedingType = FeedingType.HERBIVOROUS,
    hunger = 50
  )
  private val algae = Algae(
    base = 20,
    height = 10
  )

  private val interaction = Interaction(fish, algae)

  describe("An herbivorous fish") {
    it("should eat an algae when he is hungry") {
      val updatedFishAndAlgae = interaction.update()
      assert(updatedFishAndAlgae._1.hunger == 60)
      assert(updatedFishAndAlgae._2.isEmpty)
    }

    it("shouldn't eat an algae when he isn't hungry") {
      val newFish = Fish(
        feedingType = FeedingType.HERBIVOROUS,
        hunger = 95
      )
      val newInteraction = Interaction(newFish, algae)
      val updatedFishAndAlgae = newInteraction.update()
      assert(updatedFishAndAlgae._1.hunger == 95)
      assert(updatedFishAndAlgae._2.get.height == 10)
    }
  }
