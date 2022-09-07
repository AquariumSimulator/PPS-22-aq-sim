package model.interaction.aquariumAlgae

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}

/** Trait that models methods for updating an [[Algae]] based of the [[AquariumState]] */
trait InteractionAquariumOnAlgae:

  /** Update the [[Algae]]. Checks if the algae is alive (brightness level) and make it grow if it's possible.
    * @return
    *   a new updated [[Algae]]
    */
  def update(): Option[Algae]

/** Companion object of [[InteractionAquariumOnAlgae]] */
object InteractionAquariumOnAlgae:

  /** Create a new [[InteractionAquariumOnAlgae]] by a given [[AquariumState]] and [[Algae]]
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   the [[Algae]] that has to be updated
    * @return
    *   a new [[InteractionAquariumOnAlgae]]
    */
  def apply(aquariumState: AquariumState, algae: Algae): InteractionAquariumOnAlgae =
    InteractionAquariumOnAlgaeImpl(aquariumState, algae)

  /** Hidden implementation of [[InteractionAquariumOnAlgae]]
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   the [[Algae]] that has to be updated
    */
  private class InteractionAquariumOnAlgaeImpl(aquariumState: AquariumState, algae: Algae)
      extends InteractionAquariumOnAlgae:

    override def update(): Option[Algae] =
      if checkIfAlgaeAreDead() then Option.empty
      else Some(algae.copy(height = algae.height + calculateAlgaeGrowth()))

    /** Check if the brightness level of the aquarium is sufficient for the algae to live. If the level is equal or
      * lower that [[Algae.LOWER_BRIGHTNESS_LEVEL]] the algae dies.
      * @return
      *   true if the algae is dead otherwise it return false
      */
    def checkIfAlgaeAreDead(): Boolean =
      aquariumState.brightness match
        case brightnessLevel if brightnessLevel <= Algae.LOWER_BRIGHTNESS_LEVEL => true
        case _ => false

    /** It calculate the algae growth with the following proportion: 100% : 10 = Brightness level : X. If the algae
      * height it's equal to [[Algae.MAX_HEIGHT]] the growth it's equal to 0.
      * @return
      *   the growth calculated
      */
    def calculateAlgaeGrowth(): Int =
      val calculatePercentage = (b: Int) => (Algae.MAX_GROWTH * b) / AquariumParametersLimits.BRIGHTNESS_MAX
      algae.height match
        case Algae.MAX_HEIGHT => 0
        case brightnessLevel => calculatePercentage(brightnessLevel)
