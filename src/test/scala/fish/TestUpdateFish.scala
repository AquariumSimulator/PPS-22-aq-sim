package fish

import org.scalatest.funspec.AnyFunSpec
import model.fish._
import model.food.Food

class TestUpdateFish extends AnyFunSpec:
  describe("UpdateFish") {
    it("should return a new Fish when moving") {
      var f: Fish = Fish()
      assert(f !== UpdateFish(f).move(1))
    }

    it("should return a new Fish when updating hunger") {
      var f: Fish = Fish()
      assert(f !== UpdateFish(f).updateHunger(5))
    }

    it("should return a new Fish when eating another Fish") {
      var f: Fish = Fish()
      assert(f !== UpdateFish(f).eat(Fish()))
    }

    it("should return a new Fish when eating food") {
      var f: Fish = Fish()
      assert(f !== UpdateFish(f).eat(Food(feedingType = f.feedingType)))
    }
  }
