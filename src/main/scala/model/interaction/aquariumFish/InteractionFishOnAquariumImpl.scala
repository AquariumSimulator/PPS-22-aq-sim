package model.interaction.aquariumFish

import model.aquarium.{AquariumParametersLimits, AquariumState, UpdateAquariumState}
import model.fish.Fish
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  * @param aquariumState
  *   the current state of the aquarium that have to be updated
  * @param fish
  *   a [[Fish]] of the aquarium that influences the aquarium state
  */
class InteractionFishOnAquariumImpl(aquariumState: AquariumState, fish: Fish) extends Interaction[AquariumState]:

  override def update(): AquariumState =
    UpdateAquariumState(
      UpdateAquariumState(UpdateAquariumState(aquariumState).
        updatePh(aquariumState.ph + fish.phShift))
        .updateOxygenation(aquariumState.oxygenation + fish.oxygenShift)
    ).updateImpurity(aquariumState.impurity + fish.impurityShift)
