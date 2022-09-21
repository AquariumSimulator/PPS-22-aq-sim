package fish

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.BeforeAndAfterEach
import model.fish._
import model.aquarium.AquariumDimensions

class TestFishMovement extends AnyFunSpec with BeforeAndAfterEach:

  val multiplier: Int = 1

  describe("A Fish") {
    it("when has hunger 0, should not be alive") {
      var f: Fish = Fish()
      f = UpdateFish(f).updateHunger(0)
      assert(!f.isAlive)
    }

    it("should change speed when requested") {
      var f: Fish = Fish()
      f = UpdateFish(f).updateSpeed((1.0, 4.0))
      assert(f.speed === (1.0, 4.0))
    }

    it("should move to the expected position when requested") {
      var f: Fish = Fish(position = (1, 4), speed = (2, 3))
      f = UpdateFish(f).move(multiplier)
      assert(f.position === (3.0, 7.0))
    }

    it("should bounce back from a border with an underflow position and change speed") {
      var f: Fish = Fish(position = (-5, -6), speed = (-5, -6))
      f = UpdateFish(f).move(multiplier)
      assert(f.position === (0.0, 0.0))
      assert(f.speed._1 > 0)
      assert(f.speed._2 > 0)
    }

    it("should bounce back from a border with an overflow position and change speed") {
      var f: Fish = Fish(position = (AquariumDimensions.WIDTH + 3, AquariumDimensions.HEIGHT + 2), speed = (4, 5))
      f = UpdateFish(f).move(multiplier)
      assert(f.position === (AquariumDimensions.WIDTH, AquariumDimensions.HEIGHT))
      assert(f.speed._1 < 0)
      assert(f.speed._2 < 0)
    }
  }
