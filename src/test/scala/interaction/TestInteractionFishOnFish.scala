package interaction

import model.FeedingType
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

class TestInteractionFishOnFish extends AnyFunSpec:

  private var carnivorousFish =
    Fish(hunger = 70)

  private val herbivorousFish =
    Fish(feedingType = FeedingType.HERBIVOROUS, size = (Fish.MAX_WIDTH, Fish.MAX_HEIGHT))

  private var interaction1 = Interaction(carnivorousFish, herbivorousFish)
  private var interaction2 = Interaction(herbivorousFish, carnivorousFish)

  describe("A carnivorous fish") {
    describe("should eat an herbivorous fish when he is hungry") {
      it("if he's the one who start the interaction") {
        val tuple = interaction1.update()
        assert(tuple._1.get.hunger == 70 + Fish.MEAT_AMOUNT * herbivorousFish.size._1)
        assert(tuple._2.isEmpty)
      }

      it("if he isn't the one who start the interaction") {
        val tuple = interaction2.update()
        assert(tuple._1.get.hunger == carnivorousFish.hunger + (herbivorousFish.size._1 * Fish.MEAT_AMOUNT))
        assert(tuple._2.isEmpty)
      }
    }

    describe("shouldn't eat an herbivorous fish when he is hungry") {
      it("if he's the one who start the interaction") {
        carnivorousFish = carnivorousFish.copy(hunger = 90)
        interaction1 = Interaction(carnivorousFish, herbivorousFish)
        val tuple = interaction1.update()
        assert(tuple === (Some(carnivorousFish), Some(herbivorousFish), Option.empty))
      }

      it("if he isn't the one who start the interaction") {
        carnivorousFish = carnivorousFish.copy(hunger = 90)
        interaction2 = Interaction(herbivorousFish, carnivorousFish)
        val tuple = interaction2.update()
        assert(tuple === (Some(carnivorousFish), Some(herbivorousFish), Option.empty))
      }
    }
  }
