package interaction

import model.aquarium.AquariumParametersLimits._
import model.interaction.DeathProbabilityAlgae._
import model.interaction.DeathProbabilityFish._
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
    ProbabilityTestsUtils.probabilities(PH_MIN, PROB_PH, (MIN_SAFE_PH * multiplier).toInt)((ph: Double, prob: Double) =>
      (inc(ph, 0.1), calcProb(prob, deltaPh))
    )

  private val probabilitiesHighPh =
    ProbabilityTestsUtils.probabilities(MAX_SAFE_PH, 0, (PH_MAX - MAX_SAFE_PH * multiplier).toInt)(
      (ph: Double, prob: Double) => (inc(ph, 0.1), calcProb(prob, -deltaPh))
    )

  private val probabilitiesOxygen =
    ProbabilityTestsUtils.probabilities(
      OXYGENATION_MIN,
      PROB_OXYGEN,
      MAX_INTERVAL_TOO_LOW_OXYGENATION - OXYGENATION_MIN
    )((oxygen: Double, prob: Double) => (inc(oxygen, 1), calcProb(prob, deltaOxygen)))

  private val probabilitiesAge =
    ProbabilityTestsUtils.probabilities(0, 0, MAX_AGE_FISH)((age: Double, prob: Double) => (age + 1, prob + deltaAge))

  describe("Given a list of (ph, probability)") {
    describe("when the probability of the death of the fish is calculate on the ph") {
      it("should be equal to the precalculated one") {
        ProbabilityTestsUtils.checkForEach(probabilitiesLowPh)((ph: Double, prob: Double) =>
          TOO_LOW_PH(ph) === prob +- tolerance
        )
        ProbabilityTestsUtils.checkForEach(probabilitiesHighPh)((ph: Double, prob: Double) =>
          TOO_HIGH_PH(ph) === prob +- tolerance
        )
      }
    }
  }

  describe("Given the list of (oxygenation, probability)") {
    describe("when LOW_OXYGENATION is calculate on the oxygenation") {
      it("should be equal to the precalculated one") {
        ProbabilityTestsUtils.checkForEach(probabilitiesOxygen)((oxygen: Double, prob: Double) =>
          LOW_OXYGENATION(oxygen) === prob +- tolerance
        )
      }
    }
  }

  describe("Given the list of (age, probability)") {
    describe("when FISH_AGE is calculate on the age") {
      it("should be equal to the precalculated one") {
        ProbabilityTestsUtils.checkForEach(probabilitiesAge)((age: Double, prob: Double) =>
          FISH_AGE(age) === prob +- tolerance
        )
      }
    }
  }
