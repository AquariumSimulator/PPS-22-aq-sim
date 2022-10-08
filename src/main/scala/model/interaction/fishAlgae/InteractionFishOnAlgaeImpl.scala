package model.interaction.fishAlgae

import model.fish.Fish
import model.FeedingType
import model.Algae
import model.interaction.Interaction
import model.chronicle.Events
import mvc.MVC.model

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   the [[Fish]] that has to eat the algae
  * @param algae
  *   the [[Algae]] that has to be eaten
  */
class InteractionFishOnAlgaeImpl(fish: Fish, algae: Algae) extends Interaction[(Fish, Option[Algae])]:

  override def update(): (Fish, Option[Algae]) =
    fish.feedingType match
      case FeedingType.HERBIVOROUS if Fish.MAX_SATIETY - fish.satiety >= (algae.height * Algae.NUTRITION_AMOUNT) =>
        model.addChronicleEvent(Events.FISH_ATE_ENTITY(fish.name, algae))
        (fish.copy(satiety = fish.satiety + algae.height * Algae.NUTRITION_AMOUNT), Option.empty)
      case _ => (fish, Some(algae))
