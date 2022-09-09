package interaction.aquariumAlgae

import model.aquarium.AquariumParametersLimits.*
import model.interaction.DeathProbabilityAlgae.*
import model.interaction.DeathProbabilityFish.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestDeathProbabilityFish extends AnyFunSpec:

  private val probabilities =
    List.iterate((0: Double, PROB_PH: Double), (MIN_SAFE_PH * 10).toInt)((ph: Double, prob: Double) =>
      (ph + 0.1, prob - 0.05)
    )

  describe("Given a list of (ph, probability)") {
    describe(s"when $TOO_LOW_PH is calculate on the ph") {
      it("should be equal to the precalculated one") {
        probabilities
          .foreach((ph, prob) => assert(TOO_LOW_PH(ph) === prob +- 0.05))
      }
    }
  }
