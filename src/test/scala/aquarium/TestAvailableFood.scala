package aquarium

import model.FeedingType
import model.aquarium.{AvailableFood, UpdateAvailableFood}
import model.food.Food
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestAvailableFood extends AnyFunSpec:

  val availableFoodForAddTest = AvailableFood(Set.empty, Set.empty)

  val availableFoodForRemoveTest = AvailableFood(
    Set(Food(feedingType = FeedingType.HERBIVOROUS, position = (0, 0))),
    Set(Food(feedingType = FeedingType.HERBIVOROUS, position = (0, 0)))
  )

  val newHerbivorousElem = Food(feedingType = FeedingType.HERBIVOROUS, position = (0, 1))
  val newCarnivorousElem = Food(position = (0, 1))
  val newFoodWithAddedElem = availableFoodForAddTest.addFood(newHerbivorousElem).addFood(newCarnivorousElem)

  val newFoodWithRemovedElem = newFoodWithAddedElem.deleteFood(newHerbivorousElem).deleteFood(newCarnivorousElem)

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
  describe("When addFood is called on the herbivorous food set") {
    it(
      "should return a new AvailableFood with an updated herbivorous food set equal to the old one plus a new Food instance"
    ) {
      assert(newFoodWithAddedElem.herbivorousFood.size == availableFoodForAddTest.herbivorousFood.size + 1)
    }
    it(s"and it should contain $newHerbivorousElem") {
      assert(newFoodWithAddedElem.herbivorousFood.contains(newHerbivorousElem))
    }
  }

  describe("When removeFood is called on on the herbivorous food set") {
    it("should return a new AvailableFood with an updated herbivorous food set with one less element") {
      assert(newFoodWithRemovedElem.herbivorousFood.size == newFoodWithAddedElem.herbivorousFood.size - 1)
    }
    it(s"and it should not contain $newHerbivorousElem") {
      assert(!newFoodWithRemovedElem.herbivorousFood.contains(newHerbivorousElem))
    }
  }

  describe("When addFood is called on the carnivorous food set") {
    it(
      "should return a new AvailableFood with an updated carnivorous food set equal to the old one plus a new Food instance"
    ) {
      assert(newFoodWithAddedElem.carnivorousFood.size == availableFoodForAddTest.carnivorousFood.size + 1)
    }

    it(s"and it should contain $newCarnivorousElem ") {
      assert(newFoodWithAddedElem.carnivorousFood.contains(newCarnivorousElem))
    }
  }
  describe("When removeFood is called on on the carnivorous food set") {
    it("should return a new AvailableFood with an updated carnivorous food set with one less element") {
      assert(newFoodWithRemovedElem.carnivorousFood.size == newFoodWithAddedElem.carnivorousFood.size - 1)
    }

    it(s"and it should contain $newCarnivorousElem ") {
      assert(!newFoodWithRemovedElem.carnivorousFood.contains(newCarnivorousElem))
    }
  }
