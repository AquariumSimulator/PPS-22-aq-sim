package fish

import model.{Fish, HerbivorousFish}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

class TestFish extends AnyFunSpec with BeforeAndAfterEach:

  // HerbivorousFish is used as an example
  var f: Fish = HerbivorousFish()

  override def beforeEach(): Unit =
    f = HerbivorousFish()

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
