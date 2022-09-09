package model.interaction

import model.aquarium.AquariumParametersLimits

/** Constants that represent the probability of an algae to die */
object DeathProbabilityAlgae:

  /** Max probability of an algae to die */
  val PROB_MIN_BRIGHTNESS: Int = 5

  /** Max value of the interval of lack of brightness level */
  val MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL: Int = 11

  /** Probability of the death of an algae given the lack of brightness: (MAX_VAL - X)*RANGE_PROB/(VAL_MAX - VAL_MIN) */
  val LACK_OF_BRIGHTNESS = (brightnessLevel: Double) =>
    (MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL - brightnessLevel) * PROB_MIN_BRIGHTNESS /
      (MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL - AquariumParametersLimits.BRIGHTNESS_MIN)

object DeathProbabilityFish:

  val MIN_SAFE_PH: Double = 5.9
  val PROB_PH: Int = 3

  val TOO_LOW_PH = (phLevel: Double) =>
    (MIN_SAFE_PH - phLevel) * PROB_PH / (MIN_SAFE_PH - AquariumParametersLimits.PH_MIN)
