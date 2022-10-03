package food

import org.scalatest.funspec.AnyFunSpec

import model.food.Food
import org.scalatest.GivenWhenThen
import model.FeedingType

/** Test for [[Food]] */
class TestFood extends AnyFunSpec with GivenWhenThen:

  describe("The food model") {
    it("It has default values") {
      Given("A new food")
      val food: Food = Food()

      Then("Start at the top")
      assert(food.position._2 == 0)

      And("Nutrition amount should be positive")
      assert(food.nutritionAmount > 0)

      And("Feeding type should be Carnovirous or Herbivorous")
      assert(food.feedingType == FeedingType.CARNIVOROUS || food.feedingType == FeedingType.HERBIVOROUS)

      And("Shouldn't change oxygen level")
      assert(food.oxygenShift == 0)

      And("Should dirty the aquarium")
      assert(food.impurityShift >= 0)

      And("Shouldn't change PH level")
      assert(food.phShift == 0)
    }
  }
