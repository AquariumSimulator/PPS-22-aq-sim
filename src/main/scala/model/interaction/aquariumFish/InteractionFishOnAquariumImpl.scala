package model.interaction.aquariumFish

import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.fish.{Fish, HerbivorousFish}
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  * @param aquariumState
  *   the current state of the aquarium that have to be updated
  * @param fish
  *   a [[Fish]] of the aquarium that influences the aquarium state
  */
class InteractionFishOnAquariumImpl(aquariumState: AquariumState, fish: Fish)
    extends Interaction[AquariumState]:

  override def update(): AquariumState =

    val newImpurity = aquariumState.impurity + fish.impurityShift match
      case impurity if impurity <= AquariumParametersLimits.IMPURITY_MAX => impurity
      case _ => aquariumState.impurity

    val newOxygenation = aquariumState.oxygenation + fish.oxygenShift match
      case oxygen if oxygen >= AquariumParametersLimits.OXYGENATION_MIN => oxygen
      case _ => aquariumState.oxygenation

    val newPh = aquariumState.ph + fish.phShift match
      case ph if ph <= AquariumParametersLimits.PH_MAX => ph
      case _ => aquariumState.ph

    aquariumState.copy(impurity = newImpurity, oxygenation = newOxygenation, ph = newPh)
