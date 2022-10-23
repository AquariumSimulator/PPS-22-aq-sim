package model.interaction

import model.fish.Fish
import model.fish.Fish.MAX_SATIETY
import model.food.Food
import mvc.MVC.model
import _root_.model.chronicle.Events

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   the [[Fish]] that has to eat the food
  * @param food
  *   the [[Food]] that has to be eaten
  */
private class InteractionFishOnFoodImpl(fish: Fish, food: Food) extends Interaction[(Fish, Option[Food])]:

  override def update(): (Fish, Option[Food]) =
    if (Fish.MAX_SATIETY - fish.satiety >= food.nutritionAmount)
      model.addChronicleEvent(Events.FISH_ATE_ENTITY(fish.name, food))
      (fish.copy(satiety = MAX_SATIETY min (fish.satiety + food.nutritionAmount)), Option.empty)
    else (fish, Some(food))
