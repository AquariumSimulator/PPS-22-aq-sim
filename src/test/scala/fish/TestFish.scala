package fish

import model.{Fish, HerbivoreFish}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

class TestFish extends AnyFunSpec with BeforeAndAfterEach:

  var f: Fish = HerbivoreFish()

  override def beforeEach(): Unit =
    f = HerbivoreFish()

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
  }
