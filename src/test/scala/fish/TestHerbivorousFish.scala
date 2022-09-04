package fish

import model.fish.{Fish, HerbivorousFish}
import model.HerbivorousFood

class TestHerbivorousFish extends TestFish:

  var hf: HerbivorousFish = HerbivorousFish()

  override def beforeEach(): Unit =
    hf = HerbivorousFish()

  describe("An Herbivorous Fish") {
    it("should not eat more than max hunger") {
      val food: HerbivorousFood = HerbivorousFood()
      hf.eat(food)
      assert(hf.hunger === Fish.MAX_HUNGER)
    }
  }
