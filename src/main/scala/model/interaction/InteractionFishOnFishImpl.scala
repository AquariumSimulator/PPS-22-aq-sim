package model.interaction

import model.FeedingType
import model.aquarium.Population
import model.chronicle.Events
import model.fish.Fish
import model.interaction.Interaction
import mvc.MVC.model

/** Hidden implementation of [[Interaction]]
  * @param fish1
  *   the [[Fish]] that represents the first fish of the interaction
  * @param fish2
  *   the [[Fish]] that represents the second fish of the interaction
  */
private class InteractionFishOnFishImpl(fish1: Fish, fish2: Fish)
    extends Interaction[(Option[Fish], Option[Fish], Option[Fish])]:

  override def update(): (Option[Fish], Option[Fish], Option[Fish]) =
    (fish1.feedingType, fish2.feedingType) match
      case (x, y) if x == y => checkReproduction(fish1, fish2)
      case (x, y) if x != y => checkEatFish(fish1, fish2)
      case _ => (Option.empty, Option.empty, Option.empty)

  private def checkReproduction(fish1: Fish, fish2: Fish): (Option[Fish], Option[Fish], Option[Fish]) =
    (fish1.reproductionFactor, fish2.reproductionFactor) match
      case (x, y) if x < Fish.REPRODUCTION_COST || y < Fish.REPRODUCTION_COST =>
        (Some(fish1), Some(fish2), Option.empty)
      case _ =>
        val newFish = Fish(
          feedingType = fish1.feedingType,
          speed = Population.randomSpeed(),
          position = Population.randomPosition()
        )
        model.addChronicleEvent(Events.FISH_BIRTH(newFish.name))
        (
          Some(fish1.copy(reproductionFactor = fish1.reproductionFactor - Fish.REPRODUCTION_COST)),
          Some(fish2.copy(reproductionFactor = fish1.reproductionFactor - Fish.REPRODUCTION_COST)),
          Some(newFish)
        )

  private def checkEatFish(fish1: Fish, fish2: Fish): (Option[Fish], Option[Fish], Option[Fish]) =
    val (carnivorous, herbivorous) = checkFishPosition(fish1, fish2)
    if (isCarnivorousHungry(carnivorous, herbivorous))
      model.addChronicleEvent(Events.FISH_ATE_ENTITY(carnivorous.name, herbivorous))
      (
        Some(
          carnivorous.copy(satiety = carnivorous.satiety + (herbivorous.size._1 * Fish.MEAT_AMOUNT).toInt)
        ),
        Option.empty,
        Option.empty
      )
    else (Some(carnivorous), Some(herbivorous), Option.empty)

  private def isCarnivorousHungry(carnivorous: Fish, herbivorous: Fish): Boolean =
    (Fish.MAX_SATIETY - carnivorous.satiety) >= (herbivorous.size._1 * Fish.MEAT_AMOUNT)

  private def checkFishPosition(fish1: Fish, fish2: Fish): (Fish, Fish) =
    fish1.feedingType match
      case FeedingType.CARNIVOROUS => (fish1, fish2)
      case _ => (fish2, fish1)
