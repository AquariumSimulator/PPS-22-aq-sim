package model.interaction.aquariumFood

import model.aquarium.AquariumState
import model.food.Food
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  * @param aquariumState
  *   the current state of the aquarium that have to be updated
  * @param food
  *   a [[Food]] of the aquarium that influences the aquarium state
  */
class InteractionFoodOnAquariumImpl(aquariumState: AquariumState, food: Food) extends Interaction[AquariumState]:

  override def update(): AquariumState =
    aquariumState
      .updateImpurity(aquariumState.impurity + food.impurityShift)
