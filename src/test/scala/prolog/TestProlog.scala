package prolog

import org.scalatest.funspec.AnyFunSpec
import model.db.PrologEngine
import model.fish.Fish
import alice.tuprolog.Theory

class TestProlog extends AnyFunSpec:
  describe("A new database") {
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
  }

  describe("A database") {
    describe("when added a fish") {
      val f: Fish = Fish()
      PrologEngine.saveFish(f)
      it("should have 1 fish") {
        assert(PrologEngine.getAllFish.size == 1)
        assert(PrologEngine.getAllFish.head == f)
      }
    }

    describe("when pippo si arrabbia perchè non funziona") {
      it("should do something funny") {
        PrologEngine.engine.setTheory(new Theory("banana(ciao,virgola,virgolette)."))
        PrologEngine.engine.solve("banana(X,Y,Z).")
        println(PrologEngine.engine.getTheory())
        while (PrologEngine.engine.hasOpenAlternatives)
          //results += engine.solveNext().getSolution().toString
          println(PrologEngine.engine.solveNext())
        assert(PrologEngine.getAllFish.isEmpty)
      }
    }
  }
