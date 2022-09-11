package model.interaction.algae

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  *
  * @param aquariumState
  *   the [[AquariumState]] that has to be updated
  * @param algae
  *   algae that influences the state of the aquarium
  */
private class InteractionAlgaeOnAquariumImpl(aquariumState: AquariumState, algae: Algae)
    extends Interaction[AquariumState]:

  override def update(): AquariumState =

    val newOxygenation = aquariumState.oxygenation + algae.oxygenShift match
      case newLevel if newLevel > AquariumParametersLimits.OXYGENATION_MAX => aquariumState.oxygenation
      case newLevel => newLevel

    val newPh = aquariumState.ph + algae.phShift match
      case newLevel if newLevel < 0 => aquariumState.ph
      case newLevel => newLevel

    aquariumState.copy(oxygenation = newOxygenation, ph = newPh)
