package model.interaction

import model.aquarium.AquariumParametersLimits

/** Constants that represent the probability of an algae to die */
object DeathProbabilityAlgae:

  /** Max probability of an algae to die */
  val PROB_MIN_BRIGHTNESS: Int = 5

  /** Max value of the interval of lack of brightness level */
  val MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL: Int = 11

  /** Probability of the death of an algae given the lack of brightness */
  val LACK_OF_BRIGHTNESS: Double => Double = (brightnessLevel: Double) =>
    (MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL - brightnessLevel) * PROB_MIN_BRIGHTNESS /
      (MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL - AquariumParametersLimits.BRIGHTNESS_MIN)

/** Constants that represent the probability of a fish to die */
object DeathProbabilityFish:

  /** Min safe value of ph */
  val MIN_SAFE_PH: Double = 5.9

  /** Max safe value of ph */
  val MAX_SAFE_PH: Double = 8.5

  /** Max probability of a fish to die if the ph is too high or too low */
  val PROB_PH: Int = 3

  /** Probability of the death of a fish given a too low ph */
  val TOO_LOW_PH: Double => Double = (phLevel: Double) =>
    (MIN_SAFE_PH - phLevel) * PROB_PH / (MIN_SAFE_PH - AquariumParametersLimits.PH_MIN)

  /** Probability of the death of a fish given a too high ph */
  val TOO_HIGH_PH: Double => Double = (phLevel: Double) =>
    (phLevel - MAX_SAFE_PH) * PROB_PH / (AquariumParametersLimits.PH_MAX - MAX_SAFE_PH)

  /** Max value of the interval of lack of brightness level */
  val MAX_INTERVAL_TOO_LOW_OXYGENATION: Int = 10

  /** Max probability of a fish to die if the oxygenation is too low */
  val PROB_OXYGEN = 5

  /** Probability of the death of a fish given a too low oxygenation */
  val LOW_OXYGENATION: Double => Double = (oxygen: Double) =>
    (MAX_INTERVAL_TOO_LOW_OXYGENATION - oxygen) * PROB_OXYGEN /
      (MAX_INTERVAL_TOO_LOW_OXYGENATION - AquariumParametersLimits.OXYGENATION_MIN)

  /** Max value of the age of a fish */
  val MAX_AGE_FISH: Int = 1000

  /** Min value of the fish age at which the fish can begin to die of old age */
  val MIN_AGE_FISH: Int = 100

  /** Max probability of a fish to die if the age is 1000 */
  val PROB_AGE: Int = 100

  /** Probability of the death of a fish given its age */
  val FISH_AGE: Double => Double = (age: Double) => age * PROB_AGE / MAX_AGE_FISH

/** Constants that represent the multipliers of the fish speed */
object MultiplierVelocityFish:

  /** Max speed multiplier thanks to impurity */
  val MAX_SPEED_IMPURITY: Int = 2

  /** Multiplier of the speed calculated based on the impurity */
  val SPEED_MULTIPLIER_IMPURITY: Double => Double = (impurity: Double) =>
    (AquariumParametersLimits.IMPURITY_MAX - impurity) * MAX_SPEED_IMPURITY /
      AquariumParametersLimits.IMPURITY_MAX

  /** Max speed multiplier thanks to temperature */
  val MAX_SPEED_TEMPERATURE: Int = 2

  /** Multiplier of the speed calculated based on the temperature */
  val SPEED_MULTIPLIER_TEMPERATURE: Double => Double = (temp: Double) =>
    temp * MAX_SPEED_TEMPERATURE / AquariumParametersLimits.TEMPERATURE_MAX
