package fish

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec
import model.aquarium.AquariumDimensions
import model.fish.{Fish, UpdateFish}
import model.fish.Fish.{MAX_SATIETY, MEAT_AMOUNT}
import model.food.Food

class TestFish extends AnyFunSpec with BeforeAndAfterEach:

  var f: Fish = Fish()

  override def beforeEach(): Unit =
    f = Fish()

  describe("A new Fish") {
    it("should have age 0") {
      assert(f.age === 0)
    }

    it("should have max satiety") {
      assert(f.satiety === Fish.MAX_SATIETY)
    }

    it("should be alive") {
      assert(f.isAlive)
    }

    it("should have speed 0") {
      assert(f.speed === (0.0, 0.0))
    }

    it("should not have an empty name") {
      assert(f.name !== "")
    }

    it("should absorb oxygen") {
      assert(f.oxygenShift < 0)
    }

    it("should pollute the water") {
      assert(f.impurityShift > 0)
    }
  }

  describe("A Fish") {
    it("when has satiety 0, should not be alive") {
      val f: Fish = Fish(satiety = 0)
      assert(!f.isAlive)
    }

    it("when has satiety greater than 0, should be alive") {
      val f: Fish = Fish(satiety = 15)
      assert(f.isAlive)
    }

    it("should have more satiety after having eaten food") {
      val f: Fish = Fish(satiety = 15)
      val food: Food = Food(nutritionAmount = 10)
      assert(UpdateFish(f).eat(food).satiety === f.satiety + food.nutritionAmount)
    }

    it("should not have more than MAX_SATIETY after having eaten food") {
      val f: Fish = Fish(satiety = MAX_SATIETY - 5)
      val food: Food = Food(nutritionAmount = 10)
      assert(UpdateFish(f).eat(food).satiety === MAX_SATIETY)
    }

    it("should have more satiety after having eaten another fish") {
      val f: Fish = Fish(satiety = 15)
      val other: Fish = Fish(size = 1.5)
      assert(UpdateFish(f).eat(other).satiety === f.satiety + other.size * MEAT_AMOUNT)
    }

    it("should not have more than MAX_SATIETY after having eaten another fish") {
      val f: Fish = Fish(satiety = MAX_SATIETY - 5)
      val other: Fish = Fish(size = 1.5)
      assert(UpdateFish(f).eat(other).satiety === MAX_SATIETY)
    }
  }
