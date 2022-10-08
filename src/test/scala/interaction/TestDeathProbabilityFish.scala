package interaction

import model.aquarium.AquariumParametersLimits.*
import model.interaction.DeathProbabilityAlgae.*
import model.interaction.DeathProbabilityFish.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

/** Test for the lambda that calculate the probability of a fish to die */
class TestDeathProbabilityFish extends AnyFunSpec:

  private val tolerance = 0.05

  private val multiplier = 10
  private val deltaPh = 0.05
  private val deltaOxygen = 0.55
  private val deltaAge = 0.1
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

  private val probabilitiesAge =
    List.iterate((0, 0.0), MAX_AGE_FISH)((age: Int, prob: Double) => (age + 1, prob + deltaAge))

  describe("Given a list of ph values") {
    describe("when the probability of the death of the fish is calculate on the ph") {
      it("should be equal to the precalculated one") {
        probabilitiesLowPh
          .foreach((ph, prob) => assert(TOO_LOW_PH(ph) === prob +- tolerance))
        probabilitiesHighPh
          .foreach((ph, prob) => assert(TOO_HIGH_PH(ph) === prob +- tolerance))
      }
    }
  }

  describe("Given the list of (oxygenation, probability)") {
    describe("when LOW_OXYGENATION is calculate on the oxygenation") {
      it("should be equal to the precalculated one") {
        probabilitiesOxygen
          .foreach((oxygen, prob) => assert(LOW_OXYGENATION(oxygen) === prob +- tolerance))
      }
    }
  }

  describe("Given the list of (age, probability)") {
    describe("when FISH_AGE is calculate on the age") {
      it("should be equal to the precalculated one") {
        probabilitiesAge
          .foreach((age, prob) => assert(FISH_AGE(age) === prob +- tolerance))
      }
    }
  }
