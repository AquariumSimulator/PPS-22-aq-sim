package fish

import model.fish.{CarnivorousFish, Fish}
import model.CarnivorousFood

class TestCarnivorousFish extends TestFish:

  var hf: CarnivorousFish = CarnivorousFish()

  override def beforeEach(): Unit =
    hf = CarnivorousFish()

  describe("A Carnivorous Fish") {
    it("should not eat more than max hunger") {
      val food: CarnivorousFood = CarnivorousFood()
      hf.eat(food)
      assert(hf.hunger === Fish.MAX_HUNGER)
    }
  }
