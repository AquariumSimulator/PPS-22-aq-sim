package model.interaction.fishAlgae

import model.fish.{FeedingType, Fish}
import model.Algae
import model.interaction.Interaction

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   the [[Fish]] that has to eat the algae
  * @param algae
  *   the [[Algae]] that has to be eaten
  */
class InteractionFishOnAlgaeImpl(fish: Fish, algae: Algae) extends Interaction[Fish]:

  override def update(): Fish =
    fish.feedingType match
      case FeedingType.HERBIVOROUS if Fish.MAX_HUNGER - fish.hunger >= (algae.height * Algae.NUTRITION_AMOUNT) =>
        fish.copy(hunger = fish.hunger + algae.height * Algae.NUTRITION_AMOUNT)
      case _ => fish
