package interaction

import model.fish.Fish
import org.scalatest.funspec.AnyFunSpec
import model.fish.FeedingType
import model.interaction.Interaction

class TestInteractionFishOnFish extends AnyFunSpec:

    private val carnivorousFish = Fish(
        feedingType = FeedingType.CARNIVOROUS,
        hunger = 70,
    )

    private val herbivorousFish = Fish(
        feedingType = FeedingType.HERBIVOROUS,
        size = Fish.MAX_SIZE
    )

    private val interaction = Interaction(carnivorousFish, herbivorousFish)

    describe("A carnivorous fish") {
        it("should eat an herbivorous fish when he is hungry") {
            val deadFish = interaction.update()
            assert(deadFish.get.hunger == 0)
        }

//        it("shouldn't eat an herbivorous fish when he isn't hungry") {
//            val deadFish = interaction.update()
//            assert(deadFish.isEmpty)
//        }
    }
