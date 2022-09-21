package interaction

import model.FeedingType
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

class TestInteractionFishOnFish extends AnyFunSpec:

  private val carnivorousFish =
    Fish(feedingType = FeedingType.CARNIVOROUS, hunger = 70)

  private val herbivorousFish =
    Fish(feedingType = FeedingType.HERBIVOROUS, size = Fish.MAX_SIZE)

  private val interaction = Interaction(carnivorousFish, herbivorousFish)

  describe("A carnivorous fish") {
    it("should eat an herbivorous fish when he is hungry") {
      val tuple = interaction.update()
      val fullFish = tuple._1
      val deadFish = tuple._2
      assert(fullFish.get.hunger == 95)
      assert(deadFish.get.hunger == 0)
    }

    it("shouldn't eat an herbivorous fish when he isn't hungry") {
      val newCarnivorous =
        Fish(feedingType = FeedingType.CARNIVOROUS, hunger = 90)
      val newHerbivorous =
        Fish(feedingType = FeedingType.HERBIVOROUS, size = Fish.MAX_SIZE)
      var newInteraction = Interaction(newCarnivorous, newHerbivorous)
      var tuple = newInteraction.update()
      assert(tuple === (Option.empty, Option.empty, Option.empty))
      newInteraction = Interaction(newHerbivorous, newCarnivorous)
      tuple = newInteraction.update()
      assert(tuple === (Option.empty, Option.empty, Option.empty))
    }
  }
