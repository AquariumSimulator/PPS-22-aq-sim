package model.interaction.aquariumFish

import model.aquarium.AquariumState
import model.fish.Fish
import model.interaction.DeathProbabilityFish.*
import model.interaction.MultiplierVelocityFish.*
import model.interaction.{DeathProbabilityFish, Interaction}

import scala.util.Random

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   that has to be updated
  * @param aquariumState
  *   the current state of the aquarium
  */
class InteractionAquariumOnFishImpl(fish: Fish, aquariumState: AquariumState) extends Interaction[Option[Fish]]:

  override def update(): Option[Fish] =
    if checkIfFishIsDead() then Option.empty
    else Some(fish)

  private def checkIfFishIsDead(): Boolean = checkTooLowOxygenDeath() || checkPhDeath()

  private def checkTooLowOxygenDeath(): Boolean =
    aquariumState.oxygenation match
      case oxygen if oxygen < MAX_INTERVAL_TOO_LOW_OXYGENATION =>
        checkWithRandom(LOW_OXYGENATION(aquariumState.oxygenation))
      case _ => false

  private def checkPhDeath(): Boolean =
    aquariumState.ph match
      case ph if ph < DeathProbabilityFish.MIN_SAFE_PH =>
        checkWithRandom(DeathProbabilityFish.TOO_LOW_PH(aquariumState.ph))
      case ph if ph > DeathProbabilityFish.MAX_SAFE_PH =>
        checkWithRandom(DeathProbabilityFish.TOO_HIGH_PH(aquariumState.ph))
      case _ => false

  private def checkWithRandom(value: Double): Boolean =
    val rangeMin = 0.01
    val rangeMax = 100
    val randomValue: Double = rangeMin + (rangeMax - rangeMin) * Random.nextDouble
    randomValue < value
