package interaction.collisions

import org.scalatest.funspec.AnyFunSpec
import model.Entity
import model.fish.Fish
import model.Algae
import model.food.Food
import org.scalatest.Args
import org.scalatest.Status

class TestCollisions extends AnyFunSpec:

  // val entityTypes:List[((Double,Double),(Double,Double))=>Entity]=List(
  //     (position,size) => Fish(position=position,size=size),
  //     // (position,size)=>Algae(position=position,size=size),
  //     // (position,size)=>Food(position=position,size=size)
  // )

  describe("A Fish") {
    val first: Entity = Fish(position = (1, 1), size = (10, 10))
    describe("and another Fish") {
      val second: Entity = Fish(position = (10, 10), size = (10, 10))
      it("can collide") {
        assert(first.collidesWith(second))
      }
    }
  }

  // entityTypes.foreach(firstEntityGenerator =>
  //     entityTypes.foreach(secondEntityGenerator =>
  //         first = firstEntityGenerator((0,0),(10,10))
  //         describe("A ${first.class.getName()}") {
  //             second = secondEntityGenerator((8,8),(10,10))
  //             describe("and a ${second.class.getName()}") {
  //                 it("can overlap horizontally") {
  //                     assert(first.collidesWith(second))
  //                 }

  //                 it("can overlap vertically") {
  //                     //
  //                 }

  //                 it("can overlap diagonally") {
  //                     //
  //                 }

  //                 it("can overlap diagonally (reverse)") {
  //                     //
  //                 }
  //             }
  //         }
  //     )
  // )
