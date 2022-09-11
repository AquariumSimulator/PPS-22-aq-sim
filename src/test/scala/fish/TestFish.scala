package fish

import model.fish.*
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

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
      f = UpdateFish.apply(f).updateHunger(0)
      assert(!f.isAlive)
    }

    it("should change speed when requested") {
      f = UpdateFish.apply(f).updateSpeed((1.0, 4.0))
      assert(f.speed === (1.0, 4.0))
    }

    it("should move to the expected position when requested") {
      f = UpdateFish.apply(f).updateSpeed((1.0, 4.0))
      f = UpdateFish.apply(f).move()
      f = UpdateFish.apply(f).updateSpeed((2.0, 3.0))
      f = UpdateFish.apply(f).move()
      assert(f.position === (3.0, 7.0))
    }

    it("should bounce back from a border with an underflow position") {
      f = UpdateFish.apply(f).updateSpeed((2.0, 4.0))
      f = UpdateFish.apply(f).move()
      f = UpdateFish.apply(f).updateSpeed((-5.0, -6.0))
      f = UpdateFish.apply(f).move()
      assert(f.position === (3.0, 2.0))
    }

    it("should bounce back from a border with an overflow position") {
      f = UpdateFish.apply(f).updateSpeed((196.0, 149.0))
      f = UpdateFish.apply(f).move()
      f = UpdateFish.apply(f).updateSpeed((5.0, 7.0))
      f = UpdateFish.apply(f).move()
      assert(f.position === (199.0, 144.0))
    }

  }