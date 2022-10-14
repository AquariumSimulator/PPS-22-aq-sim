package interaction

import model.aquarium.AquariumParametersLimits._
import model.interaction.DeathProbabilityAlgae._
import model.interaction.DeathProbabilityFish._
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

/** Test for the lambda that calculate the probability of an algae to die */
class TestDeathProbabilityAlgae extends AnyFunSpec:

  private val probabilities =
    ProbabilityTestsUtils.probabilities(
      1,
      PROB_MIN_BRIGHTNESS.toDouble,
      MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL - BRIGHTNESS_MIN
    )((brightnessLevel: Double, prob: Double) => (brightnessLevel + 1, prob - 0.5))

  describe("Given a list of (brightness level, probability)") {
    describe(s"when LACK_OF_BRIGHTNESS is calculate on the brightness level") {
      it("should be equal to the precalculated one") {
        ProbabilityTestsUtils.checkForEach(probabilities)((brightnessLevel: Double, prob: Double) =>
          LACK_OF_BRIGHTNESS(brightnessLevel) == prob
        )
      }
    }
  }
