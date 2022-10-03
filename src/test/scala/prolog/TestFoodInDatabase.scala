package prolog

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterEach
import model.db.PrologEngine
import model.food.Food
import model.FeedingType

class TestFoodInDatabase extends AnyFunSpec with GivenWhenThen with BeforeAndAfterEach:

  override def afterEach(): Unit =
    PrologEngine.clear()

  describe("The PrologEngine") {
    it("should allow a food to be added") {
      Given("a new food")
      val f: Food = Food()

      When("food is added")
      PrologEngine.saveFood(f)

      Then("the food list should have size 1")
      assert(PrologEngine.getAllFood.size === 1)

      And("the only food should be the one inserted before")
      assert(PrologEngine.getAllFood.head === f)
    }

    it("should allow an herbivorous food to be added") {
      Given("an herbivorous food")
      val f: Food = Food(feedingType = FeedingType.HERBIVOROUS)

      When("food is added")
      PrologEngine.saveFood(f)

      Then("the herbivorous food list should have size 1")
      assert(PrologEngine.getAllHerbivorousFood.size === 1)
      And("the only food should be the one inserted before")
      assert(PrologEngine.getAllHerbivorousFood.head === f)

      Then("the carnivorous food list should have size 0")
      assert(PrologEngine.getAllCarnivorousFood.isEmpty)
    }

    it("should allow a carnivorous food to be added") {
      Given("a carnovorous food")
      val f: Food = Food(feedingType = FeedingType.CARNIVOROUS)

      When("food is added")
      PrologEngine.saveFood(f)

      Then("the carnivorous food list should have size 1")
      assert(PrologEngine.getAllCarnivorousFood.size === 1)
      And("the only food should be the one inserted before")
      assert(PrologEngine.getAllCarnivorousFood.head === f)

      Then("the herbivorous food list should have size 0")
      assert(PrologEngine.getAllHerbivorousFood.isEmpty)
    }
  }
