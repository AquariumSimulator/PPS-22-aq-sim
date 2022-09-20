package prolog

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterEach
import model.db.PrologEngine
import model.HerbivorousFood
import model.CarnivorousFood

class TestFoodInDatabase extends AnyFunSpec with GivenWhenThen with BeforeAndAfterEach:

  override def afterEach(): Unit =
    PrologEngine.clear

  describe("The PrologEngine") {
    it("should allow an herbivorous food to be added") {
      Given("an herbivorous fish")
      val f: HerbivorousFood = HerbivorousFood()

      When("fish is added")
      PrologEngine.saveHerbFood(f)

      Then("the herbivorous food list should have size 1")
      assert(PrologEngine.getAllHerbivorousFood.size === 1)
      And("the only food should be the one inserted before")
      assert(PrologEngine.getAllHerbivorousFood.head === f)

      Then("the carnivorous food list should have size 0")
      assert(PrologEngine.getAllCarnivorousFood.isEmpty)
    }

    it("should allow a carnivorous fish to be added") {
      Given("a carnovorous fish")
      val f: CarnivorousFood = CarnivorousFood()

      When("fish is added")
      PrologEngine.saveCarnFood(f)

      Then("the carnivorous food list should have size 1")
      assert(PrologEngine.getAllCarnivorousFood.size === 1)
      And("the only food should be the one inserted before")
      assert(PrologEngine.getAllCarnivorousFood.head === f)

      Then("the herbivorous food list should have size 0")
      assert(PrologEngine.getAllHerbivorousFood.isEmpty)
    }
  }
