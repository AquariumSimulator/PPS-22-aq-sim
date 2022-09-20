package prolog

import org.scalatest.funspec.AnyFunSpec
import model.db.PrologEngine
import model.fish.Fish
import org.scalatest.GivenWhenThen

class TestPrologEngine extends AnyFunSpec with GivenWhenThen:
  Given("a new fish")
  val f: Fish = Fish()

  it("should allow the fish to be added") {
    When("fish is added")
    PrologEngine.saveFish(f)

    Then("the fish list should have size 1")
    assert(PrologEngine.getAllFish.size == 1)

    And("the only fish should be the one inserted before")
    assert(PrologEngine.getAllFish.head == f)
  }
