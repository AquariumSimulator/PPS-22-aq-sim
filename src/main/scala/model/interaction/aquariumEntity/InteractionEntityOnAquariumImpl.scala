package model.interaction.aquariumEntity

import model.Entity
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.fish.Fish
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  * @param aquariumState
  *   the current state of the aquarium that has to be updated. The updated parameters are the PH level, the oxygenation
  *   level and the impurity level
  * @param entity
  *   a [[Entity]] of the aquarium that influences the aquarium state
  */
class InteractionEntityOnAquariumImpl(aquariumState: AquariumState, entity: Entity) extends Interaction[AquariumState]:

  override def update(): AquariumState =
    aquariumState
      .updatePh(aquariumState.ph + entity.phShift)
      .updateOxygenation(aquariumState.oxygenation + entity.oxygenShift)
      .updateImpurity(aquariumState.impurity + entity.impurityShift)
