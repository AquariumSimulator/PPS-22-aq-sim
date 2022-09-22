package fish

import model.fish._
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec
import model.aquarium.AquariumDimensions

class TestFish extends AnyFunSpec with BeforeAndAfterEach:

  var f: Fish = Fish()

  override def beforeEach(): Unit =
    f = Fish()

  describe("A new Fish") {
    it("should have age 0") {
      assert(f.age === 0)
    }

    it("should have max hunger") {
      assert(f.hunger === Fish.MAX_HUNGER)
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
    it("when has hunger 0, should not be alive") {
      var f: Fish = Fish(hunger = 0)
      assert(!f.isAlive)
    }

    it("when has hunger greater than 0, should not be alive") {
      var f: Fish = Fish(hunger = 15)
      assert(f.isAlive)
    }
  }
