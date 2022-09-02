package fish

import model.{Fish, HerbivoreFish, HerbivoreFood}

class TestHerbivoreFish extends TestFish:

  var hf: HerbivoreFish = HerbivoreFish()

  override def beforeEach(): Unit =
    hf = HerbivoreFish()

  describe("An Herbivore Fish") {
    it("should not eat more than max hunger") {
      val food: HerbivoreFood = HerbivoreFood()
      hf.eat(food)
      assert(hf.hunger === Fish.MAX_HUNGER)
    }
  }
