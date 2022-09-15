package model.interaction.aquariumAlgae

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState, UpdateAquariumState}
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  *
  * @param aquariumState
  *   the [[AquariumState]] that has to be updated
  * @param algae
  *   algae that influences the state of the aquarium
  */
class InteractionAlgaeOnAquariumImpl(aquariumState: AquariumState, algae: Algae) extends Interaction[AquariumState]:

  override def update(): AquariumState =
    UpdateAquariumState(
      UpdateAquariumState(aquariumState).
        updateOxygenation(aquariumState.oxygenation + algae.oxygenShift)
    ).updatePh(aquariumState.ph + algae.phShift)
