package model.interaction.fishFish

import model.fish.Fish
import model.FeedingType
import model.interaction.Interaction

class InteractionFishOnFishImpl(fish1: Fish, fish2: Fish) extends Interaction[(Option[Fish], Option[Fish], Option[Fish])]:

  override def update(): (Option[Fish], Option[Fish], Option[Fish]) =
    (fish1.feedingType, fish2.feedingType) match
      case (FeedingType.HERBIVOROUS, FeedingType.HERBIVOROUS) =>
        checkReproduction(fish1, fish2)
      case (FeedingType.CARNIVOROUS, FeedingType.CARNIVOROUS) =>
        checkReproduction(fish1, fish2)
      case (FeedingType.CARNIVOROUS, FeedingType.HERBIVOROUS) =>
        checkEatFish(fish1, fish2)
      case (FeedingType.HERBIVOROUS, FeedingType.CARNIVOROUS) =>
        checkEatFish(fish2, fish1)
      case _ => (Option.empty, Option.empty, Option.empty)

  private def checkReproduction(fish1: Fish, fish2: Fish): (Option[Fish], Option[Fish], Option[Fish]) =
    (fish1.reproductionFactor, fish2.reproductionFactor) match
      case (x, y) if x < Fish.REPRODUCTION_COST || y < Fish.REPRODUCTION_COST => 
        (Option.empty, Option.empty, Option.empty)
      case _ => (
          Some(fish1.copy(reproductionFactor = fish1.reproductionFactor - Fish.REPRODUCTION_COST)),
          Some(fish2.copy(reproductionFactor = fish1.reproductionFactor - Fish.REPRODUCTION_COST)),
          Some(Fish()),
        )

  private def checkEatFish(carnivorous: Fish, herbivorous: Fish): (Option[Fish], Option[Fish], Option[Fish]) =
    println("Is carnivorous hungry? " + isCarnivorousHungry(carnivorous, herbivorous) + ", hunger: " + carnivorous.hunger)
    if (isCarnivorousHungry(carnivorous, herbivorous))
      (
        Some(carnivorous.copy(hunger = carnivorous.hunger + (herbivorous.size * Fish.MEAT_AMOUNT).toInt)),
        Some(herbivorous.copy(hunger = 0)),
        Option.empty,
      )
    else (Option.empty, Option.empty, Option.empty)

  private def isCarnivorousHungry(carnivorous: Fish, herbivorous: Fish): Boolean =
    (Fish.MAX_HUNGER - carnivorous.hunger) >= (herbivorous.size * Fish.MEAT_AMOUNT)
