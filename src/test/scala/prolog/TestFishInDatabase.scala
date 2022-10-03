package prolog

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterEach
import model.db.PrologEngine
import model.fish.Fish
import model.FeedingType

class TestFishInDatabase extends AnyFunSpec with GivenWhenThen with BeforeAndAfterEach:

  override def afterEach(): Unit =
    PrologEngine.clear()

  describe("The PrologEngine") {
    it("should allow a fish to be added") {
      Given("a new fish")
      val f: Fish = Fish()

      When("fish is added")
      PrologEngine.saveFish(f)

      Then("the fish list should have size 1")
      assert(PrologEngine.getAllFish().size === 1)

      And("the only fish should be the one inserted before")
      assert(PrologEngine.getAllFish().head === f)
    }

    it("should allow an herbivorous fish to be added") {
      Given("an herbivorous fish")
      val f: Fish = Fish(feedingType = FeedingType.HERBIVOROUS)

      When("fish is added")
      PrologEngine.saveFish(f)

      Then("the herbivorous fish list should have size 1")
      assert(PrologEngine.getAllHerbivorousFish().size === 1)
      And("the only fish should be the one inserted before")
      assert(PrologEngine.getAllHerbivorousFish().head === f)

      Then("the carnivorous fish list should have size 0")
      assert(PrologEngine.getAllCarnivorousFish().isEmpty)
    }

    it("should allow a carnivorous fish to be added") {
      Given("a carnovorous fish")
      val f: Fish = Fish(feedingType = FeedingType.CARNIVOROUS)

      When("fish is added")
      PrologEngine.saveFish(f)

      Then("the carnivorous fish list should have size 1")
      assert(PrologEngine.getAllCarnivorousFish().size === 1)
      And("the only fish should be the one inserted before")
      assert(PrologEngine.getAllCarnivorousFish().head === f)

      Then("the herbivorous fish list should have size 0")
      assert(PrologEngine.getAllHerbivorousFish().isEmpty)
    }
  }
