package fish

import org.scalatest.funspec.AnyFunSpec
import model.fish._
import model.aquarium.AquariumDimensions

class TestFishMovement extends AnyFunSpec:

  val multiplier: Int = 1

  describe("A Fish") {
    it("should move to the expected position when requested") {
      var f: Fish = Fish(position = (1, 4), speed = (2, 3), size = (10, 10))
      f = f.move(multiplier)
      assert(f.position === (3.0, 7.0))
    }

    describe("when its x coordinate") {
      describe("is less than 0") {
        val f: Fish = Fish(position = (-5, 12), speed = (-2, -3)).move(multiplier)
        it("should get back to 0") {
          assert(f.position._1 === 0)
        }
        it("should bounce back with a positive speed") {
          assert(f.speed._1 === 2)
        }
      }

      describe("is inside the borders") {
        val initialSpeed: (Double, Double) = (-2, -3)
        val f: Fish = Fish(position = (5, 12), speed = initialSpeed).move(multiplier)
        it("should not change speed") {
          assert(f.speed === initialSpeed)
        }
      }

      describe("is greater than AquariumDimensions.WIDTH") {
        val f: Fish = Fish(position = (AquariumDimensions.WIDTH + 5, 12), speed = (2, -3)).move(multiplier)
        it("should get back to AquariumDimensions.WIDTH") {
          assert(f.position._1 === AquariumDimensions.WIDTH - f.size._1)
        }
        it("should bounce back with a negative speed") {
          assert(f.speed._1 === -2)
        }
      }
    }

    describe("when its y coordinate") {
      describe("is less than 0") {
        val f: Fish = Fish(position = (5, -12), speed = (-2, -3)).move(multiplier)
        it("should get back to 0") {
          assert(f.position._2 === 0)
        }
        it("should bounce back with a positive speed") {
          assert(f.speed._2 === 3)
        }
      }

      describe("is inside the borders") {
        val initialSpeed: (Double, Double) = (-2, -3)
        val f: Fish = Fish(position = (5, 12), speed = initialSpeed).move(multiplier)
        it("should not change speed") {
          assert(f.speed === initialSpeed)
        }
      }

      describe("is greater than AquariumDimensions.HEIGHT") {
        val f: Fish = Fish(position = (12, AquariumDimensions.HEIGHT + 5), speed = (-2, 3)).move(multiplier)
        it("should get back to AquariumDimensions.HEIGHT") {
          assert(f.position._2 === AquariumDimensions.HEIGHT - f.size._2)
        }
        it("should bounce back with a negative speed") {
          assert(f.speed._2 === -3)
        }
      }
    }

    it("should move cover 2 times the distance if multiplier is doubled") {
      var f: Fish = Fish(position = (0, 0), speed = (1, 1))
      f = f.move(1)
      assert(f.position == (1, 1))
      f = f.move(2)
      assert(f.position == (3, 3))
    }

    it("should keep the same speed while moving with multiplier 1") {
      val initialSpeed: (Double, Double) = (1, 1)
      var f: Fish = Fish(position = (0, 0), speed = initialSpeed)
      for (_ <- 1 to 10)
        f = f.move(1)
        assert(f.speed === initialSpeed)
    }

    it("should keep the same speed while moving with multiplier 1.5") {
      val initialSpeed: (Double, Double) = (2, 3)
      var f: Fish = Fish(position = (0, 0), speed = initialSpeed)
      for (_ <- 1 to 10)
        f = f.move(1.5)
        assert(f.speed === initialSpeed)
    }

    it("should not move when moved with multiplier 0") {
      val initialSpeed: (Double, Double) = (2, 3)
      val initialPosition: (Double, Double) = (4, 7)
      var f: Fish = Fish(position = initialPosition, speed = initialSpeed)
      f = f.move(0)
      assert(f.position === initialPosition)
      assert(f.speed === initialSpeed)
    }
  }
