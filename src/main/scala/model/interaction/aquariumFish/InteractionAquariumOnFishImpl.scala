package model.interaction.aquariumFish

import model.aquarium.AquariumState
import model.chronicle.Events
import model.fish.Fish
import model.interaction.DeathProbabilityFish.*
import model.interaction.MultiplierVelocityFish.*
import model.interaction.{DeathProbabilityFish, Interaction}
import mvc.MVC.model

import scala.util.Random

/** Hidden implementation of [[Interaction]]
  * @param fish
  *   that has to be updated. The interaction check if the fish is dead due to the oxygenation level or the PH level
  * @param aquariumState
  *   the current state of the aquarium
  */
class InteractionAquariumOnFishImpl(fish: Fish, aquariumState: AquariumState) extends Interaction[Option[Fish]]:

  override def update(): Option[Fish] =
    if checkIfFishIsDead() then
      model.addChronicleEvent(Events.ENTITY_DEATH(fish))
      Option.empty
    else Some(fish)

  /** This method
    * @return
    *   if the fish is dead or not
    */
  private def checkIfFishIsDead(): Boolean = checkTooLowOxygenDeath() || checkPhDeath()

  /** Check if the fish is dead due to the too low level of oxygenation
    * @return
    *   true if the fish is dead otherwise it returns false
    */
  private def checkTooLowOxygenDeath(): Boolean =
    aquariumState.oxygenation match
      case oxygen if oxygen < MAX_INTERVAL_TOO_LOW_OXYGENATION =>
        checkWithRandom(LOW_OXYGENATION(aquariumState.oxygenation))
      case _ => false

  /** Check if the fish is dead due to the too low or too high level of ph
    *
    * @return
    *   true if the fish is dead otherwise it returns false
    */
  private def checkPhDeath(): Boolean =
    aquariumState.ph match
      case ph if ph < DeathProbabilityFish.MIN_SAFE_PH =>
        checkWithRandom(DeathProbabilityFish.TOO_LOW_PH(aquariumState.ph))
      case ph if ph > DeathProbabilityFish.MAX_SAFE_PH =>
        checkWithRandom(DeathProbabilityFish.TOO_HIGH_PH(aquariumState.ph))
      case _ => false

  /** Calculate if the fish is dead or not. If the random number is lower than the probability the fish is dead.
    * @param value
    *   the calculated probability
    * @return
    *   true if the fish is dead otherwise it returns false
    */
  private def checkWithRandom(value: Double): Boolean =
    val rangeMin = 0.01
    val rangeMax = 100
    val randomValue: Double = rangeMin + (rangeMax - rangeMin) * Random.nextDouble
    randomValue < value
