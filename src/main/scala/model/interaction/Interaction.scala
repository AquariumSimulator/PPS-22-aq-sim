package model.interaction

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}
import model.fish.Fish
import model.interaction.aquariumAlgae.InteractionAlgaeOnAquariumImpl
import model.interaction.aquariumAlgae.InteractionAquariumOnAlgaeImpl
import model.interaction.aquariumFish.InteractionFishOnAquariumImpl
import model.interaction.fishAlgae.InteractionFishOnAlgaeImpl
import model.interaction.aquariumFish.InteractionAquariumOnFishImpl
import model.interaction.fishFish.InteractionFishOnFishImpl

/** Trait that models an interaction between two elements
  * @tparam A
  *   type of the modified element
  */
trait Interaction[A]:
  /** Update an [[A]].
    * @return
    *   a new updated [[A]]
    */
  def update(): A

/** Companion object of [[Interaction]] */
object Interaction:

  /** Create a new interaction meant to update the oxygenation level and the ph.
    * @param aquariumState
    *   the current state of the aquarium that has to be updated
    * @param algae
    *   that modifies the aquarium state
    * @return
    *   a new interaction[[Interaction]]
    */

  def apply(aquariumState: AquariumState, algae: Algae): Interaction[AquariumState] =
    InteractionAlgaeOnAquariumImpl(aquariumState, algae)

  /** Create a new interaction meant to check if the algae is alive (brightness level) and make it grow if it's
    * possible.
    *
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   that has to be updated
    * @return
    *   a new interaction
    */
  def apply(algae: Algae, aquariumState: AquariumState): Interaction[Option[Algae]] =
    InteractionAquariumOnAlgaeImpl(aquariumState, algae)

  /** Create a new interaction meant to update the oxygenation level and the ph and the impurity level.
    * @param aquariumState
    *   that has to be updated
    * @param fish
    *   that modifies the aquarium state
    * @return
    *   a new interaction
    */
  def apply(aquariumState: AquariumState, fish: Fish): Interaction[AquariumState] =
    InteractionFishOnAquariumImpl(aquariumState, fish)

  /** Create a new [[Interaction]] by a given [[Fish]] and [[AquariumState]]. This interaction is meant to check if the
    * fish is alive and update its speed.
    *
    * @param fish
    *   that has to be updated
    * @param aquariumState
    *   the current state of the aquarium
    * @return
    *   a new [[Interaction]]
    */
  def apply(fish: Fish, aquariumState: AquariumState): Interaction[Option[Fish]] =
    InteractionAquariumOnFishImpl(fish, aquariumState)

  /** Create a new [[Interaction]] by a given [[Fish]] and [[Algae]]. This interaction is meant to check if the
    * herbivorous fish is hungry and, in that case, have to eat it.
    *
    * @param fish
    *   that has to eat the algae
    * @param algae
    *   that, possibly, has to be eaten
    * @return
    *   a new [[Interaction]]
    */
  def apply(fish: Fish, algae: Algae): Interaction[(Fish, Algae)] =
    InteractionFishOnAlgaeImpl(fish, algae)

  /** Create a new [[Interaction]] between two [[Fish]]. If the 2 fishes are both herbivorous or both carnivorous they
    * can only reproduce. It the 2 fishes are different: if the carnivorous one is hungry, the herbivorous one is eaten,
    * otherwise nothing happens.
    *
    * @param fish1
    *   the first fish
    * @param fish2
    *   the second fish
    * @return
    *   a new [[Interaction]]
    */
  def apply(fish1: Fish, fish2: Fish): Interaction[(Option[Fish], Option[Fish], Option[Fish])] =
    InteractionFishOnFishImpl(fish1, fish2)
