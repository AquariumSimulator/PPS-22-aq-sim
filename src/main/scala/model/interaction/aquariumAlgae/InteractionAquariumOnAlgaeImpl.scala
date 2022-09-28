package model.interaction.aquariumAlgae

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.interaction.{DeathProbabilityAlgae, Interaction}
import model.interaction.Interaction

import scala.util.Random

/** Hidden implementation of [[Interaction]]
  *
  * @param aquariumState
  *   the current state of the aquarium
  * @param algae
  *   the [[Algae]] that has to be updated
  */
class InteractionAquariumOnAlgaeImpl(aquariumState: AquariumState, algae: Algae) extends Interaction[Option[Algae]]:

  override def update(): Option[Algae] =
    if checkIfAlgaeAreDead() then Option.empty
    else Some(algae.copy(height = algae.height + calculateAlgaeGrowth()))

  def checkIfAlgaeAreDead(): Boolean =
    aquariumState.brightness match
      case brightnessLevel if brightnessLevel <= DeathProbabilityAlgae.MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL =>
        Random.between(0, 100) < DeathProbabilityAlgae.LACK_OF_BRIGHTNESS(brightnessLevel)
      case _ => false

  def calculateAlgaeGrowth(): Int =
    val calculatePercentage = (b: Int) =>
      Algae.MIN_GROWTH +
        ((b - Algae.MIN_GROWTH) * (Algae.MAX_GROWTH - Algae.MIN_GROWTH)) /
        (AquariumParametersLimits.BRIGHTNESS_MAX - Algae.MIN_GROWTH)
    algae.height match
      case h if h >= Algae.MAX_HEIGHT => 0
      case brightnessLevel =>
        calculatePercentage(brightnessLevel)
