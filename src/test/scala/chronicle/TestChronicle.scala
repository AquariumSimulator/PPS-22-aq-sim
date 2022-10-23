package chronicle

import model.chronicle.{Chronicle, UpdateChronicle}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

class TestChronicle extends AnyFunSpec:

  var chronicle = Chronicle()

  describe("A new Chronicle") {
    it("should be empty") {
      assert(chronicle.events.isEmpty)
    }

    it("should have one element after the addition of it") {
      chronicle = chronicle.addEvent("Fish1 is born")
      assert(chronicle.events.size == 1)
      assert(chronicle.events.last === "Fish1 is born")
    }

    it("should have more element after multiple additions") {
      chronicle = chronicle.addEvent("A new fish is born")
      chronicle = chronicle.addEvent("Fish1 is dead")
      chronicle = chronicle.addEvent("Fish2 ate Fish1")
      assert(chronicle.events.size == 4)
    }
  }
