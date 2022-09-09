package interaction.aquariumAlgae

import model.aquarium.AquariumParametersLimits.*
import model.interaction.DeathProbabilityAlgae.*
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

class TestDeathProbabilityAlgae extends AnyFunSpec:

  private val probabilities =
    List.iterate((1, PROB_MIN_BRIGHTNESS.toDouble), MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL - BRIGHTNESS_MIN)(
      (brightnessLevel: Int, prob: Double) => (brightnessLevel + 1, prob - 0.5)
    )

  describe("Given a list of (brightness level, probability)") {
    describe(s"when $LACK_OF_BRIGHTNESS is calculate on the brightness level") {
      it("should be equal to the precalculated one") {
        probabilities
          .foreach((brightnessLevel, prob) => assert(LACK_OF_BRIGHTNESS(brightnessLevel) == prob))
      }
    }
  }
