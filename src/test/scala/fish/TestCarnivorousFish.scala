package fish

import model.fish.{CarnivorousFish, Fish, UpdateCarnivorousFish}
import model.CarnivorousFood

class TestCarnivorousFish extends TestFish:

  var cf: CarnivorousFish = CarnivorousFish()

  override def beforeEach(): Unit =
    cf = CarnivorousFish()

  describe("A Carnivorous Fish") {
    it("should not eat more than max hunger") {
      val food: CarnivorousFood = CarnivorousFood()
      cf = UpdateCarnivorousFish.apply(cf).eat(food)
      assert(cf.hunger === Fish.MAX_HUNGER)
    }
  }
