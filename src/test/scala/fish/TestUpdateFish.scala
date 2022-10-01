package fish

import org.scalatest.funspec.AnyFunSpec
import model.fish._
import model.food.Food

class TestUpdateFish extends AnyFunSpec:
  describe("UpdateFish") {
    it("should return a new Fish with a new position when moving") {
      val f: Fish = Fish(speed = (1, 1))
      assert(f.position != UpdateFish(f).move(1).position)
    }

    it("should return a new Fish with an updated satiety when updating satiety") {
      val f: Fish = Fish()
      assert(f.satiety != UpdateFish(f).updateSatiety(5).satiety)
    }

    it("should return a new Fish with an updated satiety when eating another Fish") {
      val f: Fish = Fish(satiety = 0)
      assert(f.satiety !== UpdateFish(f).eat(Fish()).satiety)
    }

    it("should return a new Fish with an updated satiety when eating food") {
      val f: Fish = Fish(satiety = 5)
      assert(f.satiety != UpdateFish(f).eat(Food(feedingType = f.feedingType)).satiety)
    }
  }
