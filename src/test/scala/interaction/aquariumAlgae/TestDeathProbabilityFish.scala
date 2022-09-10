package interaction.aquariumAlgae

import model.aquarium.AquariumParametersLimits.*
import model.interaction.DeathProbabilityAlgae.*
import model.interaction.DeathProbabilityFish.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestDeathProbabilityFish extends AnyFunSpec:

  private val tolerance = 0.05

  private val multiplier = 10
  private val deltaPh = 0.05
  private val deltaOxygen = 0.55
  private val inc = (n: Double, inc: Double) => n + inc
  private val calcProb = (n: Double, delta: Double) => n - delta

  private val probabilitiesLowPh =
    List.iterate((PH_MIN: Double, PROB_PH: Double), (MIN_SAFE_PH * multiplier).toInt)((ph: Double, prob: Double) =>
      (inc(ph, 0.1), calcProb(prob, deltaPh))
    )

  private val probabilitiesHighPh =
    List.iterate((MAX_SAFE_PH: Double, 0: Double), (PH_MAX - MAX_SAFE_PH * multiplier).toInt)(
      (ph: Double, prob: Double) => (inc(ph, 0.1), calcProb(prob, -deltaPh))
    )

  private val probabilitiesOxygen =
    List.iterate((OXYGENATION_MIN: Double, PROB_OXYGEN: Double), MAX_INTERVAL_TOO_LOW_OXYGENATION - OXYGENATION_MIN)(
      (oxygen: Double, prob: Double) => (inc(oxygen, 1), calcProb(prob, deltaOxygen))
    )

  describe(s"Given a list of ph values") {
    describe(s"when the probability of the death of the fish is calculate on the ph") {
      it("should be equal to the precalculated one") {
        probabilitiesLowPh
          .foreach((ph, prob) => assert(TOO_LOW_PH(ph) === prob +- tolerance))
        probabilitiesHighPh
          .foreach((ph, prob) => assert(TOO_HIGH_PH(ph) === prob +- tolerance))
      }
    }
  }

  describe(s"Given the list of (oxygenation, probability) $probabilitiesOxygen") {
    describe(s"when $LOW_OXYGENATION is calculate on the oxygenation") {
      it("should be equal to the precalculated one") {
        probabilitiesOxygen
          .foreach((oxygen, prob) => assert(LOW_OXYGENATION(oxygen) === prob +- tolerance))
      }
    }
  }
