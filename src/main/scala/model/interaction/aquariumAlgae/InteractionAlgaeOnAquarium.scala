package model.interaction.aquariumAlgae

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}

/** Trait that models methods for updating an [[AquariumState]] based of the [[Algae]] */
trait InteractionAlgaeOnAquarium:
  /** Update the [[AquariumState]]. Updates the oxygenation level and the ph.
    * @return
    *   a new updated [[AquariumState]]
    */
  def update(): AquariumState

/** Companion object of [[InteractionAlgaeOnAquarium]] */
object InteractionAlgaeOnAquarium:

  /** Create a new [[InteractionAlgaeOnAquarium]] by a given [[AquariumState]] and [[Algae]]
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   the [[Algae]] that has to be updated
    * @return
    *   a new [[InteractionAlgaeOnAquarium]]
    */
  def apply(aquariumState: AquariumState, algae: Algae): InteractionAlgaeOnAquarium =
    InteractionAlgaeOnAquariumImpl(aquariumState, algae)

  /** Hidden implementation of [[InteractionAlgaeOnAquarium]]
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   the [[Algae]] that has to be updated
    */
  private class InteractionAlgaeOnAquariumImpl(aquariumState: AquariumState, algae: Algae)
      extends InteractionAlgaeOnAquarium:

    override def update(): AquariumState =

      val newOxygenation = aquariumState.oxygenation + algae.oxygenShift match
        case newLevel if newLevel > AquariumParametersLimits.OXYGENATION_MAX => aquariumState.oxygenation
        case newLevel => newLevel

      val newPh = aquariumState.ph + algae.phShift match
        case newLevel if newLevel < 0 => aquariumState.ph
        case newLevel => newLevel

      aquariumState.copy(oxygenation = newOxygenation, ph = newPh)
