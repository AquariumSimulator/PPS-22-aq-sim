import model.{Fish, CarnivoreFish, CarnivoreFood}

class TestCarnivoreFish extends TestFish:

  var hf: CarnivoreFish = CarnivoreFish()

  override def beforeEach(): Unit =
    hf = CarnivoreFish()

  describe("An Herbivore Fish") {
    it("should not eat more than max hunger") {
      val food: CarnivoreFood = CarnivoreFood()
      hf.eat(food)
      assert(hf.hunger === Fish.MAX_HUNGER)
    }
  }
