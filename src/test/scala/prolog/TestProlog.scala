package prolog

import org.scalatest.funspec.AnyFunSpec
import model.db.PrologEngine
import model.fish.Fish
import org.scalatest.GivenWhenThen

class TestProlog extends AnyFunSpec with GivenWhenThen:
  describe("A new database") {
    Given("the PrologEngine")
    it("should have 0 fish") {
      assert(PrologEngine.getAllFish.isEmpty)
    }

    it("should have 0 algae") {
      assert(PrologEngine.getAllAlgae.isEmpty)
    }

    it("should have 0 carnivorous food") {
      assert(PrologEngine.getAllCarnFood.isEmpty)
    }

    it("should have 0 herbivorous food") {
      assert(PrologEngine.getAllHerbFood.isEmpty)
    }
    
    Given("a new fish")
    val f: Fish = Fish()

    it ("should allow the fish to be added"){
      When("fish is added")
      PrologEngine.saveFish(f)

      Then("the fish list should have size 1")
      assert(PrologEngine.getAllFish.size == 1)

      And("the only fish should be the one inserted before")
      assert(PrologEngine.getAllFish.head == f)
    }
  }
