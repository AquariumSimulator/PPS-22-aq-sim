package prolog

import org.scalatest.funspec.AnyFunSpec
import model.db.PrologEngine
import model.fish.Fish

class TestNewDatabase extends AnyFunSpec:
  describe("A new database") {
    it("should have 0 fish") {
      assert(PrologEngine.getAllFish().isEmpty)
    }

    it("should have 0 herbivorous fish") {
      assert(PrologEngine.getAllHerbivorousFish().isEmpty)
    }

    it("should have 0 carnivorous fish") {
      assert(PrologEngine.getAllCarnivorousFish().isEmpty)
    }

    it("should have 0 algae") {
      assert(PrologEngine.getAllAlgae().isEmpty)
    }

    it("should have 0 carnivorous food") {
      assert(PrologEngine.getAllCarnivorousFood().isEmpty)
    }

    it("should have 0 herbivorous food") {
      assert(PrologEngine.getAllHerbivorousFood().isEmpty)
    }
  }
