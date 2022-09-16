package prolog

import org.scalatest.funspec.AnyFunSpec
import model.db.PrologEngine
import model.fish.Fish

class TestProlog extends AnyFunSpec:
  describe("A new database") {
    it("should have 0 fish") {
      assert(PrologEngine.getAllFish.isEmpty)
    }

    it("should have 0 alage") {
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
  }
