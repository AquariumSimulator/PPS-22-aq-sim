package interaction

import model.FeedingType
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

class TestInteractionFishOnFish extends AnyFunSpec:

  private var carnivorousFish =
    Fish(hunger = 70)

  private var herbivorousFish =
    Fish(feedingType = FeedingType.HERBIVOROUS, size = Fish.MAX_SIZE)

  private var interaction = Interaction(carnivorousFish, herbivorousFish)

  describe("A carnivorous fish") {
    it("should eat an herbivorous fish when he is hungry") {
      val tuple = interaction.update()
      val fullFish = tuple._1
      val deadFish = tuple._2
      assert(fullFish.get.hunger == 95)
      assert(deadFish.get.hunger == 0)
    }

    it("shouldn't eat an herbivorous fish when he isn't hungry") {
      carnivorousFish = carnivorousFish.copy(hunger = 90)
      interaction = Interaction(carnivorousFish, herbivorousFish)
      var tuple = interaction.update()
      assert(tuple === (Option.empty, Option.empty, Option.empty))
      interaction = Interaction(herbivorousFish, carnivorousFish)
      tuple = interaction.update()
      assert(tuple === (Option.empty, Option.empty, Option.empty))
    }
  }
