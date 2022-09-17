package model.interaction.fishFish

import model.fish.{FeedingType, Fish}
import model.interaction.Interaction

class InteractionFishOnFishImpl(fish1: Fish, fish2: Fish) extends Interaction[(Option[Fish], Option[Fish])]:

    override def update(): (Option[Fish], Option[Fish]) =
        (fish1.feedingType, fish2.feedingType) match
            case (FeedingType.HERBIVOROUS, FeedingType.HERBIVOROUS) => (Option.empty, Option.empty)
            case (FeedingType.CARNIVOROUS, FeedingType.CARNIVOROUS) => (Option.empty, Option.empty)
            case (FeedingType.CARNIVOROUS, FeedingType.HERBIVOROUS) =>
                checkEatFish(fish1, fish2)
            case (FeedingType.HERBIVOROUS, FeedingType.CARNIVOROUS) =>
                checkEatFish(fish2, fish1)
            case _ => (Option.empty, Option.empty)

    private def checkEatFish(carnivorous: Fish, herbivorous: Fish): (Option[Fish], Option[Fish]) =
        println("Is carnivorous hungry? " + isCarnivorousHungry(carnivorous, herbivorous) + ", hunger: " + carnivorous.hunger)
        if (isCarnivorousHungry(carnivorous, herbivorous))
            (
              Some(carnivorous.copy(hunger = carnivorous.hunger + (herbivorous.size * Fish.MEAT_AMOUNT).toInt)),
              Some(herbivorous.copy(hunger = 0))
            )
        else (Option.empty, Option.empty)

    private def isCarnivorousHungry(carnivorous: Fish, herbivorous: Fish): Boolean =
        (Fish.MAX_HUNGER - carnivorous.hunger) >= (herbivorous.size * Fish.MEAT_AMOUNT)