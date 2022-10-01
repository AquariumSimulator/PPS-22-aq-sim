package interaction

import model.FeedingType
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

class TestInteractionFishOnFish extends AnyFunSpec:

  private var carnivorousFish =
    Fish(satiety = 70)

  private val herbivorousFish =
    Fish(feedingType = FeedingType.HERBIVOROUS, size = Fish.MAX_SIZE)

  private var interaction1 = Interaction(carnivorousFish, herbivorousFish)
  private var interaction2 = Interaction(herbivorousFish, carnivorousFish)

  describe("A carnivorous fish") {
    describe("should eat an herbivorous fish when he is hungry") {
      it("if he's the one who start the interaction") {
        val tuple = interaction1.update()
        assert(tuple._1.get.satiety == 95)
        assert(tuple._2.isEmpty)
      }

      it("if he isn't the one who start the interaction") {
        val tuple = interaction2.update()
        assert(tuple._1.get.satiety == 95)
        assert(tuple._2.isEmpty)
      }
    }

    describe("shouldn't eat an herbivorous fish when he is hungry") {
      it("if he's the one who start the interaction") {
        carnivorousFish = carnivorousFish.copy(satiety = 90)
        interaction1 = Interaction(carnivorousFish, herbivorousFish)
        var tuple = interaction1.update()
        assert(tuple === (Option.empty, Option.empty, Option.empty))
      }

      it("if he isn't the one who start the interaction") {
        carnivorousFish = carnivorousFish.copy(satiety = 90)
        interaction2 = Interaction(herbivorousFish, carnivorousFish)
        var tuple = interaction2.update()
        assert(tuple === (Option.empty, Option.empty, Option.empty))
      }
    }
  }
