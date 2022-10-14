package interaction

import model.aquarium.AquariumParametersLimits.*
import model.interaction.MultiplierVelocityFish.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

import scala.language.postfixOps
import scala.runtime.stdLibPatches.Predef.assert

/** Test for the lambda that calculate the multiplier of a fish speed */
class TestMultiplierVelocityFish extends AnyFunSpec:

  private val deltaTemp = 0.07
  private val deltaImpurity = 0.02

  private val tolerance = 0.1

  private val multipliersTemperature =
    ProbabilityTestsUtils.probabilities(0, 0, TEMPERATURE_MAX)((temperature: Double, prob: Double) =>
      (temperature + 1, prob + deltaTemp)
    )

  private val multipliersImpurity =
    ProbabilityTestsUtils.probabilities(0, MAX_SPEED_IMPURITY, IMPURITY_MAX)((impurity: Double, prob: Double) =>
      (impurity + 1, prob - deltaImpurity)
    )

  describe("Given a list of temperature") {
    describe(s"when the velocity multiplier is calculated on the temperature") {
      it("should be equal to the precalculated one") {
        ProbabilityTestsUtils.checkForEach(multipliersTemperature)((temp: Double, prob: Double) =>
          SPEED_MULTIPLIER_TEMPERATURE(temp) === prob +- tolerance
        )
      }
    }
  }
  describe("Given a list of impurity") {
    describe(s"when the velocity multiplier is calculated on the impurity") {
      it("should be equal to the precalculated one") {
        ProbabilityTestsUtils.checkForEach(multipliersImpurity)((impurity: Double, prob: Double) =>
          SPEED_MULTIPLIER_IMPURITY(impurity) === prob +- tolerance
        )
      }
    }
  }
