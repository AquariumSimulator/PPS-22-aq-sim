package model.interaction

import model.{Algae, FeedingType}
import model.chronicle.Events
import model.fish.Fish
import model.interaction.Interaction
import mvc.MVC.model

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   the [[Fish]] that has to eat the algae
  * @param algae
  *   the [[Algae]] that has to be eaten
  */
private class InteractionFishOnAlgaeImpl(fish: Fish, algae: Algae) extends Interaction[(Fish, Option[Algae])]:

  override def update(): (Fish, Option[Algae]) =
    fish.feedingType match
      case FeedingType.HERBIVOROUS if isFishHungry =>
        model.addChronicleEvent(Events.FISH_ATE_ENTITY(fish.name, algae))
        (fish.copy(satiety = fish.satiety + algae.height * Algae.NUTRITION_AMOUNT), Option.empty)
      case _ => (fish, Some(algae))

  private def isFishHungry: Boolean =
    Fish.MAX_SATIETY - fish.satiety >= (algae.height * Algae.NUTRITION_AMOUNT)
