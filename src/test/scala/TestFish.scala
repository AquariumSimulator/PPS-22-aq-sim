import org.scalatest.funspec.AnyFunSpec
import org.scalatest.BeforeAndAfterEach

import model.Fish

class TestFish extends AnyFunSpec with BeforeAndAfterEach:

  var f: Fish = Fish()

  override def beforeEach(): Unit =
    f = Fish()

  describe("A new Fish") {
    it("should have age 0") {
      assert(f.age == 0)
    }

    it("should have max hunger") {
      assert(f.hunger == Fish.MAX_HUNGER)
    }

    it("should be alive") {
      assert(f.isAlive)
    }

    it("should have speed 0") {
      assert(f.speed == (0.0, 0.0))
    }

    it("should not have an empty name") {
      assert(f.name != "")
    }
  }
