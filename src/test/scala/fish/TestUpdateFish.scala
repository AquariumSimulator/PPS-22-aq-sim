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

    it("should return a new Fish with an updated hunger when updating hunger") {
      val f: Fish = Fish()
      assert(f.hunger != UpdateFish(f).updateHunger(5).hunger)
    }

    it("should return a new Fish with an updated hunger when eating another Fish") {
      val f: Fish = Fish(hunger = 0)
      assert(f.hunger !== UpdateFish(f).eat(Fish()).hunger)
    }

    it("should return a new Fish with an updated hunger when eating food") {
      val f: Fish = Fish(hunger = 5)
      assert(f.hunger != UpdateFish(f).eat(Food(feedingType = f.feedingType)).hunger)
    }
  }
