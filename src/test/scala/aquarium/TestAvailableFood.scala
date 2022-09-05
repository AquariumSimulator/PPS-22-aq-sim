package aquarium

import model.aquarium.{AvailableFood, UpdateAvailableFood}
import model.{CarnivorousFood, Food, HerbivorousFood}
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestAvailableFood extends AnyFunSpec:

  val availableFood = AvailableFood(Set.empty, Set.empty)

  val availableFoodRemove = AvailableFood(
    Set(HerbivorousFood((0, 0)), HerbivorousFood((1, 1))),
    Set(CarnivorousFood((0, 0)), CarnivorousFood((1, 1)))
  )

  val updateAvailableFoodHerbivorousAdd = UpdateAvailableFood(availableFood.herbivorousFood)
  val updateAvailableFoodHerbivorousRemove = UpdateAvailableFood(availableFoodRemove.herbivorousFood)
  val newElemHerbivorous = HerbivorousFood((0, 1))
  val newHerbivorousFoodWithAddedElem = updateAvailableFoodHerbivorousAdd.addFood(newElemHerbivorous)
  val removeElemHerbivorous = HerbivorousFood((0, 0))
  val newHerbivorousFoodWithRemovedElem = updateAvailableFoodHerbivorousRemove.deleteFood(removeElemHerbivorous)

  val updateAvailableFoodCarnivorousAdd = UpdateAvailableFood(availableFood.carnivorousFood)
  val updateAvailableFoodCarnivorousRemove = UpdateAvailableFood(availableFoodRemove.carnivorousFood)
  val newElemCarnivorous = CarnivorousFood(0, 1)
  val newCarnivorousFoodWithAddedElem = updateAvailableFoodCarnivorousAdd.addFood(newElemCarnivorous)
  val removeElemCarnivorous = CarnivorousFood((0, 0))
  val newCarnivorousFoodWithRemovedElem = updateAvailableFoodCarnivorousRemove.deleteFood(removeElemCarnivorous)

  describe("A new instance of AvailableFood") {
    describe("built with two empty set") {
      it("should have an empty herbivorousFood set") {
        assert(availableFood.herbivorousFood.isEmpty)
      }

      it("should have an empty carnivorousFood set") {
        assert(availableFood.carnivorousFood.isEmpty)
      }
    }
  }

  describe("A new instance of UpdateAvailableFood") {
    describe("when addFood is called on the herbivorous food set") {
      it("should return a new Set[Food] equal to the old one plus a new Food instance") {
        assert(newHerbivorousFoodWithAddedElem.size == availableFood.herbivorousFood.size + 1)
      }

      it(s"should return a new Set[Food] that contain $newElemHerbivorous ") {
        assert(newHerbivorousFoodWithAddedElem.contains(newElemHerbivorous))
      }
    }

    describe(s"when removeFood is called on $newHerbivorousFoodWithRemovedElem") {
      it("should return a new Set[Food] with one less element") {
        assert(newHerbivorousFoodWithRemovedElem.size == availableFoodRemove.herbivorousFood.size - 1)
      }

      it(s"should return a new Set[Food] that doesn't contain $newElemHerbivorous ") {
        assert(!newHerbivorousFoodWithRemovedElem.contains(newElemHerbivorous))
      }
    }

    describe("when addFood is called on the carnivorous food set") {
      it("should return a new Set[Food] equal to the old one plus a new Food instance") {
        assert(newCarnivorousFoodWithAddedElem.size == availableFood.carnivorousFood.size + 1)
      }

      it(s"should return a new Set[Food] that contain $newElemCarnivorous ") {
        assert(newCarnivorousFoodWithAddedElem.contains(newElemCarnivorous))
      }
    }
    describe(s"when removeFood is called on $newCarnivorousFoodWithRemovedElem") {
      it("should return a new Set[Food] with one less element") {
        assert(newCarnivorousFoodWithRemovedElem.size == availableFoodRemove.carnivorousFood.size - 1)
      }

      it(s"should return a new Set[Food] that doesn't contain $newElemCarnivorous ") {
        assert(!newCarnivorousFoodWithRemovedElem.contains(newElemCarnivorous))
      }
    }
  }
