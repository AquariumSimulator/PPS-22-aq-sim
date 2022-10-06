package aquarium

import model.FeedingType
import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.food.Food
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interface [[AvailableFood]] */
class TestAvailableFood extends AnyFunSpec:

  private val AquariumForAddTest = Aquarium(0, 0, 0)

  private val newHerbivorousFood = Food(feedingType = FeedingType.HERBIVOROUS, position = (0, 1))
  private val newCarnivorousFood = Food(position = (0, 1))
  private var newFoodWithAddedElem =
    AquariumForAddTest.copy(availableFood = AquariumForAddTest.addFood(newHerbivorousFood))
  newFoodWithAddedElem = newFoodWithAddedElem.copy(availableFood = newFoodWithAddedElem.addFood(newCarnivorousFood))

  private val newFoodWithRemovedElem = AquariumForAddTest
    .copy(availableFood = AquariumForAddTest.deleteFood(newHerbivorousFood))
    .copy(availableFood = AquariumForAddTest.deleteFood(newCarnivorousFood))

  describe("A new instance of Aquarium") {
    it("should have an empty available food set") {
      assert(AquariumForAddTest.availableFood.isEmpty)
    }
  }
  describe("When addFood is called to add a new instance of herbivorous food") {
    it(
      "should return a new available food set with an updated set equal to the old one plus a new Food instance"
    ) {
      assert(newFoodWithAddedElem.herbivorousFood.size == AquariumForAddTest.herbivorousFood.size + 1)
    }
    it(s"and it should contain $newHerbivorousFood") {
      assert(newFoodWithAddedElem.herbivorousFood.contains(newHerbivorousFood))
    }
  }

  describe("When deleteFood is called to remove an herbivorous food instance") {
    it("should return a new available food set with an updated set with one less element") {
      assert(newFoodWithRemovedElem.herbivorousFood.size == newFoodWithAddedElem.herbivorousFood.size - 1)
    }
    it(s"and it should not contain $newHerbivorousFood") {
      assert(!newFoodWithRemovedElem.herbivorousFood.contains(newHerbivorousFood))
    }
  }

  describe("When addFood is called to add a new instance of carnivorous food") {
    it(
      "should return a new available food set with an updated set equal to the old one plus a new Food instance"
    ) {
      assert(newFoodWithAddedElem.carnivorousFood.size == AquariumForAddTest.carnivorousFood.size + 1)
    }

    it(s"and it should contain $newCarnivorousFood ") {
      assert(newFoodWithAddedElem.carnivorousFood.contains(newCarnivorousFood))
    }
  }
  describe("When deleteFood is called to remove an carnivorous food instance") {
    it("should return a new available food set with an updated set with one less element") {
      assert(newFoodWithRemovedElem.carnivorousFood.size == newFoodWithAddedElem.carnivorousFood.size - 1)
    }

    it(s"and it should contain $newCarnivorousFood ") {
      assert(!newFoodWithRemovedElem.carnivorousFood.contains(newCarnivorousFood))
    }
  }
