package prolog

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterEach
import model.db.PrologEngine
import model.Algae

class TestAlgaeInDatabase extends AnyFunSpec with GivenWhenThen with BeforeAndAfterEach:

  override def afterEach(): Unit =
    PrologEngine.clear

  describe("The PrologEngine") {
    it("should allow an algae to be added") {
      val a: Algae = Algae(10)

      When("fish is added")
      PrologEngine.saveAlgae(a)

      Then("the fish list should have size 1")
      assert(PrologEngine.getAllAlgae.size === 1)

      And("the only fish should be the one inserted before")
      assert(PrologEngine.getAllAlgae.head === a)
    }
  }
