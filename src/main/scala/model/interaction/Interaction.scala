package model.interaction

import model.Algae
import model.aquarium.{AquariumParametersLimits, AquariumState}

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

/** Companion object of [[InteractionAlgaeOnAquarium]] */
object Interaction:

  /** Create a new [[Interaction]] by a given [[AquariumState]] and [[Algae]]. The interaction is meant to update the
    * oxygenation level and the ph.
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   the [[Algae]] that has to be updated
    * @return
    *   a new [[Interaction]]
    */

  def apply(aquariumState: AquariumState, algae: Algae): Interaction[AquariumState] =
    InteractionAlgaeOnAquariumImpl(aquariumState, algae)

  /** Create a new [[Interaction]] by a given [[Algae]] and [[AquariumState]]. This interaction is meant to checks if
    * the algae is alive (brightness level) and make it grow if it's possible.
    *
    * @param aquariumState
    *   the current state of the aquarium
    * @param algae
    *   the [[Algae]] that has to be updated
    * @return
    *   a new [[InteractionAquariumOnAlgae]]
    */
  def apply(algae: Algae, aquariumState: AquariumState): Interaction[Option[Algae]] =
    InteractionAquariumOnAlgaeImpl(aquariumState, algae)
