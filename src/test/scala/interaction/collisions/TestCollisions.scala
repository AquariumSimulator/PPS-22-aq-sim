package interaction.collisions

import org.scalatest.funspec.AnyFunSpec
import model.Entity
import model.fish.Fish
import model.Algae
import model.food.Food
import org.scalatest.Args
import org.scalatest.Status

class TestCollisions extends AnyFunSpec:

  describe("A Fish") {
    val first: Entity = Fish(position = (1, 146), size = (10, 10))
    describe("and another Fish") {
      val second: Entity = Fish(position = (8, 154), size = (10, 10))
      it("can collide") {
        assert(first.collidesWith(second))
      }
    }

    describe("and an Algae") {
      val second: Entity = Algae(base = 2, height = 5)
      it("can collide") {
        assert(first.collidesWith(second))
      }
    }

    describe("and a Food") {
      val second: Entity = Food(position = (3, 149))
      it("can collide") {
        assert(first.collidesWith(second))
      }
    }
  }
