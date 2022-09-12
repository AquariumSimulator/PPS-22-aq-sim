package model.interaction

import model.aquarium.AquariumState
import model.fish.Fish
import model.interaction.DeathProbabilityFish.*
import model.interaction.Interaction
import model.interaction.MultiplierVelocityFish.*

import scala.util.Random

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   that has to be updated
  * @param aquariumState
  *   the current state of the aquarium
  */
private class InteractionAquariumOnFishImpl(fish: Fish, aquariumState: AquariumState) extends Interaction[Option[Fish]]:

  override def update(): Option[Fish] =
    checkIfFishIsDead() match
      case false =>
        val multiplier =
          SPEED_MULTIPLIER_TEMPERATURE(aquariumState.temperature) * SPEED_MULTIPLIER_IMPURITY(aquariumState.impurity)
        Some(fish.copy(speed = (fish.speed._1 * multiplier, fish.speed._2 * multiplier)))
      case _ => Option.empty

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
    Random.between(1, 100) < value
