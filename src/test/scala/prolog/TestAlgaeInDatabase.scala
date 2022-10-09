package prolog

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterEach
import model.db.PrologEngine
import model.Algae

class TestAlgaeInDatabase extends AnyFunSpec with GivenWhenThen with BeforeAndAfterEach:

  override def beforeEach(): Unit =
    PrologEngine.clear()

  describe("The PrologEngine") {
    it("should allow an algae to be added") {
      val a: Algae = Algae(10)

      When("Algae is added")
      PrologEngine.saveAlgae(a)

      Then("the Algae list should have size 1")
      assert(PrologEngine.getAllAlgae().size === 1)

      And("the only Algae should be the one inserted before")
      assert(PrologEngine.getAllAlgae().head === a)
    }

    it("should return 0 when requested an algae from the wrong iteration") {
      val a: Algae = Algae(10)
      PrologEngine.saveAlgae(a)
      assert(PrologEngine.getAllAlgae(1).isEmpty)
    }

    it("should return the algae from the right iteration") {
      val a: Algae = Algae()
      PrologEngine.saveAlgae(a, 1)
      assert(PrologEngine.getAllAlgae(1).size === 1)
      assert(PrologEngine.getAllAlgae(1).head === a)
    }
  }
