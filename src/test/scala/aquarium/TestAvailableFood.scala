package aquarium

import aquarium.{AvailableFood, UpdateAvailableFood}
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
      val newElemHerbivorous = HerbivorousFood()
      val newHerbivorousFood = updateAvailableFoodHerbivorousAdd.addFood(newElemHerbivorous)
      it("should return a new Seq[Food] equal to the old one plus a new Food instance") {
        assert(newHerbivorousFood.size == availableFood.herbivorousFood.size + 1)
      }

      it(s"should return a new Seq[Food] that contain $newElemHerbivorous ") {
        assert(newHerbivorousFood.contains(newElemHerbivorous))
      }
    }
    describe("when addFood is called on the carnivorous food seq") {
      val newElemCarnivorous = CarnivorousFood()
      val newCarnivorousFood = updateAvailableFoodCarnivorousAdd.addFood(newElemCarnivorous)
      it("should return a new Seq[Food] equal to the old one plus a new Food instance") {
        assert(newCarnivorousFood.size == availableFood.carnivorousFood.size + 1)
      }

      it(s"should return a new Seq[Food] that contain $newElemCarnivorous ") {
        assert(newCarnivorousFood.contains(newElemCarnivorous))
      }
    }
  }
