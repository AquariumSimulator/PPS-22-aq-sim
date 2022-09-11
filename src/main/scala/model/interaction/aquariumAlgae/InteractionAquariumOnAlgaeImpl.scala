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
 class InteractionAquariumOnAlgaeImpl(aquariumState: AquariumState, algae: Algae)
    extends Interaction[Option[Algae]]:

  override def update(): Option[Algae] =
    if checkIfAlgaeAreDead() then Option.empty
    else Some(algae.copy(height = algae.height + calculateAlgaeGrowth()))

  /** Check if the brightness level of the aquarium is sufficient for the algae to live. If the level is equal or lower
    * that [[Algae.LOWER_BRIGHTNESS_LEVEL]] the algae dies.
    *
    * @return
    *   true if the algae is dead otherwise it return false
    */
  def checkIfAlgaeAreDead(): Boolean =
    aquariumState.brightness match
      case brightnessLevel if brightnessLevel <= DeathProbabilityAlgae.MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL =>
        Random.between(0, 100) < DeathProbabilityAlgae.LACK_OF_BRIGHTNESS(brightnessLevel)
      case _ => false

  /** It calculate the algae growth with the following proportion: 100% : 10 = Brightness level : X. If the algae height
    * it's equal to [[Algae.MAX_HEIGHT]] the growth it's equal to 0.
    *
    * @return
    *   the growth calculated
    */
  def calculateAlgaeGrowth(): Int =
    val calculatePercentage = (b: Int) => (Algae.MAX_GROWTH * b) / AquariumParametersLimits.BRIGHTNESS_MAX
    algae.height match
      case Algae.MAX_HEIGHT => 0
      case brightnessLevel => calculatePercentage(brightnessLevel)
