package interaction

import model.FeedingType
import model.fish.Fish
import model.interaction.Interaction
import org.scalatest.funspec.AnyFunSpec

class TestReproduction extends AnyFunSpec:

  private var carnivorousFish1 =
    Fish(reproductionFactor = 70)

  private var carnivorousFish2 =
    Fish(reproductionFactor = 70)

  private var herbivorousFish1 =
    Fish(feedingType = FeedingType.HERBIVOROUS, reproductionFactor = 70)

  private var herbivorousFish2 =
    Fish(feedingType = FeedingType.HERBIVOROUS, reproductionFactor = 70)

  private var interactionC = Interaction(carnivorousFish1, carnivorousFish2)

  private var interactionH = Interaction(herbivorousFish1, herbivorousFish2)

  describe("Two carnivorous fish") {
    it("should reproduce when they can") {
      val tuple = interactionC.update()
      assert(tuple._1.get.reproductionFactor === 20)
      assert(tuple._2.get.reproductionFactor === 20)
      assert(tuple._3.isDefined)
    }

    it("shouldn't reproduce when they can't") {
      carnivorousFish1 = Fish(reproductionFactor = 40)
      carnivorousFish2 = Fish(reproductionFactor = 40)
      interactionC = Interaction(carnivorousFish1, carnivorousFish2)
      val tuple = interactionC.update()
      assert(tuple._1.isEmpty)
      assert(tuple._2.isEmpty)
      assert(tuple._3.isEmpty)
    }

    describe("Two herbivorous fish") {
      it("should reproduce when they can") {
        val tuple = interactionH.update()
        assert(tuple._1.get.reproductionFactor === 20)
        assert(tuple._2.get.reproductionFactor === 20)
        assert(tuple._3.isDefined)
      }

      it("shouldn't reproduce when they can't") {
        herbivorousFish1 = herbivorousFish1.copy(reproductionFactor = 40)
        herbivorousFish2 = herbivorousFish2.copy(reproductionFactor = 40)
        interactionH = Interaction(carnivorousFish1, carnivorousFish2)
        val tuple = interactionH.update()
        assert(tuple._1.isEmpty)
        assert(tuple._2.isEmpty)
        assert(tuple._3.isEmpty)
      }
    }
  }