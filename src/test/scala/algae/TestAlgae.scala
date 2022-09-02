package algae

import algae.*
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[Algae]] */
class TestAlgae extends AnyFunSpec:

  val base: Double = 5.0
  val height: Int = 2

  val algae: Algae = Algae(
    base,
    height
  )

  describe("A new Algae") {
    describe("When initialized") {
      it(s"Should have position equals to ($base, 0)") {
        assert(algae.position == (base, 0))
      }
      it(s"Should have height equals to $height") {
        assert(algae.height == height)
      }
      it("Should have oxygenShift positive") {
        assert(algae.oxygenShift >= 0)
      }
      it("Should have impurityShift equals to 0") {
        assert(algae.impurityShift == 0)
      }
      it("Should have phShift negative") {
        assert(algae.phShift <= 0)
      }
    }
  }
