package aquarium

import model.aquarium.{AvailableFood, UpdateAvailableFood}
import model.{CarnivorousFood, Food, HerbivorousFood}
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestAvailableFood extends AnyFunSpec:

  val availableFood = AvailableFood(Seq.empty, Seq.empty)
  val availableFoodRemove = AvailableFood(
    Seq(HerbivorousFood(), HerbivorousFood()),
    Seq(CarnivorousFood(), CarnivorousFood())
  )

  val updateAvailableFoodHerbivorousAdd = UpdateAvailableFood(availableFood.herbivorousFood)
  val updateAvailableFoodHerbivorousRemove = UpdateAvailableFood(availableFoodRemove.herbivorousFood)
  val updateAvailableFoodCarnivorousAdd = UpdateAvailableFood(availableFood.carnivorousFood)
  val updateAvailableFoodCarnivorousRemove = UpdateAvailableFood(availableFoodRemove.carnivorousFood)

  val newElemHerbivorous = HerbivorousFood()
  val newHerbivorousFoodWithAddedElem = updateAvailableFoodHerbivorousAdd.addFood(newElemHerbivorous)
  val newHerbivorousFoodWithRemovedElem = updateAvailableFoodHerbivorousRemove.deleteFood(newElemHerbivorous)

  val newElemCarnivorous = CarnivorousFood()
  val newCarnivorousFoodWithAddedElem = updateAvailableFoodCarnivorousAdd.addFood(newElemCarnivorous)
  val newCarnivorousFoodWithRemovedElem = updateAvailableFoodCarnivorousRemove.deleteFood(newElemCarnivorous)

  describe("A new instance of AvailableFood") {
    describe("built with two empty Seq") {
      it("should have an empty herbivorousFood Seq") {
        assert(availableFood.herbivorousFood.isEmpty)
        assert(availableFoodRemove.herbivorousFood.size == 2)
      }

      it("should have an empty carnivorousFood Seq") {
        assert(availableFood.carnivorousFood.isEmpty)
        assert(availableFoodRemove.carnivorousFood.size == 2)
      }
    }
  }

  describe("A new instance of UpdateAvailableFood") {
    describe("when addFood is called on the herbivorous food seq") {
      it("should return a new Seq[Food] equal to the old one plus a new Food instance") {
        assert(newHerbivorousFoodWithAddedElem.size == availableFood.herbivorousFood.size + 1)
      }

      it(s"should return a new Seq[Food] that contain $newElemHerbivorous ") {
        assert(newHerbivorousFoodWithAddedElem.contains(newElemHerbivorous))
      }
    }

    describe(s"when removeFood is called on $newHerbivorousFoodWithAddedElem") {
      it("should return a new Seq[Food] equal to the old one") {
        assert(newHerbivorousFoodWithRemovedElem.size == availableFood.herbivorousFood.size)
      }

      it(s"should return a new Seq[Food] that doesn't contain $newElemHerbivorous ") {
        assert(!newHerbivorousFoodWithRemovedElem.contains(newElemHerbivorous))
      }
    }

    describe("when addFood is called on the carnivorous food seq") {
      it("should return a new Seq[Food] equal to the old one plus a new Food instance") {
        assert(newCarnivorousFoodWithAddedElem.size == availableFood.carnivorousFood.size + 1)
      }

      it(s"should return a new Seq[Food] that contain $newElemCarnivorous ") {
        assert(newCarnivorousFoodWithAddedElem.contains(newElemCarnivorous))
      }
    }
    describe(s"when removeFood is called on $newCarnivorousFoodWithRemovedElem") {
      it("should return a new Seq[Food] equal to the old one") {
        assert(newCarnivorousFoodWithRemovedElem.size == availableFood.carnivorousFood.size)
      }

      it(s"should return a new Seq[Food] that doesn't contain $newElemCarnivorous ") {
        assert(!newCarnivorousFoodWithRemovedElem.contains(newElemCarnivorous))
      }
    }
  }
