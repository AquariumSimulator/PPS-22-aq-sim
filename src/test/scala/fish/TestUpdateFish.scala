package fish

import org.scalatest.funspec.AnyFunSpec
import model.fish.{Fish, UpdateFish}
import model.food.Food
import model.FeedingType

/** Test for the update of [[Fish]] */
class TestUpdateFish extends AnyFunSpec:
  describe("UpdateFish") {
    it("should return a new Fish with a new position when moving") {
      val f: Fish = Fish(speed = (1, 1))
      assert(f.position != f.move(1).position)
    }

    it("should return a new Fish with an updated satiety when updating satiety") {
      val f: Fish = Fish()
      assert(f.satiety != f.updateSatiety(5).satiety)
      assert(f.updateSatiety(5).satiety == 5)
    }

    it("should return a new Fish with an updated satiety when eating food") {
      val f: Fish = Fish(satiety = 5)
      assert(f.satiety != f.eat(Food(feedingType = f.feedingType)).satiety)
    }
  }
