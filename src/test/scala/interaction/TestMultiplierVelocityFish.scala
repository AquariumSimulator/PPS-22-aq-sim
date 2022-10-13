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
    List.iterate((0: Int, 0: Double), TEMPERATURE_MAX)((temperature: Int, prob: Double) =>
      (temperature + 1, prob + deltaTemp)
    )

  private val multipliersImpurity =
    List.iterate((0: Int, MAX_SPEED_IMPURITY: Double), TEMPERATURE_MAX)((temperature: Int, prob: Double) =>
      (temperature + 1, prob - deltaImpurity)
    )

  describe("Given a list of temperature") {
    describe(s"when the velocity multiplier is calculated on the temperature") {
      it("should be equal to the precalculated one") {
        multipliersTemperature
          .foreach((temp, prob) => assert(SPEED_MULTIPLIER_TEMPERATURE(temp) === prob +- tolerance))
      }
    }
  }
  describe("Given a list of impurity") {
    describe(s"when the velocity multiplier is calculated on the impurity") {
      it("should be equal to the precalculated one") {
        multipliersImpurity
          .foreach((temp, prob) => assert(SPEED_MULTIPLIER_IMPURITY(temp) === prob +- tolerance))
      }
    }
  }
