package model.interaction.aquariumAlgae

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.chronicle.Events
import model.interaction.{DeathProbabilityAlgae, Interaction}
import mvc.MVC.model

import scala.util.Random

/** Hidden implementation of [[Interaction]]
  *
  * @param algae
  *   the [[Algae]] that has to be updated. The interaction check if the alga is dead and calculate its growth.
  * @param aquariumState
  *   the current state of the aquarium
  */
class InteractionAquariumOnAlgaeImpl(algae: Algae, aquariumState: AquariumState) extends Interaction[Option[Algae]]:

  override def update(): Option[Algae] =
    if checkIfAlgaeAreDead() then
      model.addChronicleEvent(Events.ENTITY_DEATH(algae))
      Option.empty
    else Some(algae.copy(height = algae.height + calculateAlgaeGrowth()))

  /** Check if the algae is dead due to the lack of brightness
    * @return
    *   true if the algae is dead otherwise it returns false
    */
  def checkIfAlgaeAreDead(): Boolean =
    aquariumState.brightness match
      case brightnessLevel if brightnessLevel <= DeathProbabilityAlgae.MAX_BRIGHTNESS_LEVEL_PROB_INTERVAL =>
        Random.between(0, 100) < DeathProbabilityAlgae.LACK_OF_BRIGHTNESS(brightnessLevel)
      case _ => false

  /** Calculate the growth of the algae thanks the level of brightness
    * @return
    *   the growth
    */
  def calculateAlgaeGrowth(): Int =
    val calculatePercentage = (b: Int) =>
      Algae.MIN_GROWTH +
        ((b - Algae.MIN_GROWTH) * (Algae.MAX_GROWTH - Algae.MIN_GROWTH)) /
        (AquariumParametersLimits.BRIGHTNESS_MAX - Algae.MIN_GROWTH)
    algae.height match
      case h if h >= Algae.MAX_HEIGHT => 0
      case brightnessLevel =>
        calculatePercentage(brightnessLevel)
