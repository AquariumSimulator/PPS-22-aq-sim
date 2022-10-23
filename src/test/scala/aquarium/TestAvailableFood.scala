package aquarium

import model.FeedingType
import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.food.Food
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

/** Test for the interface [[AvailableFood]] */
class TestAvailableFood extends AnyFunSpec:

  private val aquarium = Aquarium(0, 0, 0)

  private val herbivorousFood = Food(feedingType = FeedingType.HERBIVOROUS, position = (0, 1))
  private val carnivorousFood = Food(position = (0, 1))

  private val aquariumWithAddedElem = aquarium.addFood(herbivorousFood).addFood(carnivorousFood)
  private val aquariumWithRemovedElem = aquarium.deleteFood(herbivorousFood).deleteFood(carnivorousFood)

  describe(s"A new instance of ${Aquarium.getClass.getName}") {
    it("should have an empty available food set") {
      assert(aquarium.availableFood.isEmpty)
    }
  }
  describe("When addFood is called to add herbivorous food") {
    it(
      s"should return an updated available food set equal to the old one plus a new ${Food.getClass.getName} instance"
    ) {
      assert(aquariumWithAddedElem.herbivorousFood.size == aquarium.herbivorousFood.size + 1)
    }
    it(s"and it should contain $herbivorousFood") {
      assert(aquariumWithAddedElem.herbivorousFood.contains(herbivorousFood))
    }
  }

  describe("When deleteFood is called to remove an herbivorous food") {
    it("should return an updated available food set with one less element") {
      assert(aquariumWithRemovedElem.herbivorousFood.size == aquariumWithAddedElem.herbivorousFood.size - 1)
    }
    it(s"and it should not contain $herbivorousFood") {
      assert(!aquariumWithRemovedElem.herbivorousFood.contains(herbivorousFood))
    }
  }

  describe("When addFood is called to add a carnivorous food") {
    it(
      s"should return an updated available food set equal to the old one plus a new ${Food.getClass.getName} instance"
    ) {
      assert(aquariumWithAddedElem.carnivorousFood.size == aquarium.carnivorousFood.size + 1)
    }

    it(s"and it should contain $carnivorousFood ") {
      assert(aquariumWithAddedElem.carnivorousFood.contains(carnivorousFood))
    }
  }
  describe("When deleteFood is called to remove a carnivorous food") {
    it("should return an updated available food set with one less element") {
      assert(aquariumWithRemovedElem.carnivorousFood.size == aquariumWithAddedElem.carnivorousFood.size - 1)
    }

    it(s"and it should contain $carnivorousFood ") {
      assert(!aquariumWithRemovedElem.carnivorousFood.contains(carnivorousFood))
    }
  }
