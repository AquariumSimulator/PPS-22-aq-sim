package aquarium

import model.aquarium.{AvailableFood, UpdateAvailableFood}
import model.{CarnivorousFood, Food, HerbivorousFood}
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestAvailableFood extends AnyFunSpec:

  val availableFoodForAddTest = AvailableFood(Set.empty, Set.empty)

  val availableFoodForRemoveTest = AvailableFood(
    Set(HerbivorousFood((0, 0)), HerbivorousFood((1, 1))),
    Set(CarnivorousFood((0, 0)), CarnivorousFood((1, 1)))
  )

  val newHerbivorousElem = HerbivorousFood((0, 1))
  val newCarnivorousElem = CarnivorousFood(0, 1)
  val newFoodWithAddedElem = availableFoodForAddTest.addFood(newHerbivorousElem).addFood(newCarnivorousElem)
  val removeHerbivorousElem = HerbivorousFood((0, 0))
  val removeCarnivorousElem = CarnivorousFood((0, 0))
  val newFoodWithRemovedElem =
    availableFoodForRemoveTest.deleteFood(removeHerbivorousElem).deleteFood(removeCarnivorousElem)

  describe("A new instance of AvailableFood") {
    describe("built with two empty set") {
      it("should have an empty herbivorousFood set") {
        assert(availableFoodForAddTest.herbivorousFood.isEmpty)
      }

      it("should have an empty carnivorousFood set") {
        assert(availableFoodForAddTest.carnivorousFood.isEmpty)
      }
    }
  }

  describe("A new instance of UpdateAvailableFood") {
    describe("when addFood is called on the herbivorous food set") {
      it("should return a new Set[Food] equal to the old one plus a new Food instance") {
        assert(newFoodWithAddedElem.herbivorousFood.size == availableFoodForAddTest.herbivorousFood.size + 1)
      }

      it(s"should return a new Set[Food] that contain $newHerbivorousElem ") {
        assert(newFoodWithAddedElem.herbivorousFood.contains(newHerbivorousElem))
      }
    }

    describe(s"when removeFood is called on ${newFoodWithRemovedElem.herbivorousFood}") {
      it("should return a new Set[Food] with one less element") {
        assert(newFoodWithRemovedElem.herbivorousFood.size == availableFoodForRemoveTest.herbivorousFood.size - 1)
      }

      it(s"should return a new Set[Food] that doesn't contain $newHerbivorousElem ") {
        assert(!newFoodWithRemovedElem.herbivorousFood.contains(newHerbivorousElem))
      }
    }

    describe("when addFood is called on the carnivorous food set") {
      it("should return a new Set[Food] equal to the old one plus a new Food instance") {
        assert(newFoodWithAddedElem.carnivorousFood.size == availableFoodForAddTest.carnivorousFood.size + 1)
      }

      it(s"should return a new Set[Food] that contain $newCarnivorousElem ") {
        assert(newFoodWithAddedElem.carnivorousFood.contains(newCarnivorousElem))
      }
    }
    describe(s"when removeFood is called on ${newFoodWithRemovedElem.carnivorousFood}") {
      it("should return a new Set[Food] with one less element") {
        assert(newFoodWithRemovedElem.carnivorousFood.size == availableFoodForRemoveTest.carnivorousFood.size - 1)
      }

      it(s"should return a new Set[Food] that doesn't contain $newCarnivorousElem ") {
        assert(!newFoodWithRemovedElem.carnivorousFood.contains(newCarnivorousElem))
      }
    }
  }
