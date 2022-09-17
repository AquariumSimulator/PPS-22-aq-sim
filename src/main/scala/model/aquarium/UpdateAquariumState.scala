package model.aquarium

/** Trait that models methods for modifying aquarium parameters */
trait UpdateAquariumState:

  /** Set the impurity to a given value
    * @param newImpurity
    *   the new impurity level of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateImpurity(newImpurity: Double): AquariumState

  /** Set the temperature to a given value
    * @param newTemperature
    *   the new temperature of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateTemperature(newTemperature: Double): AquariumState

  /** Set the brightness to a given value
    * @param newBrightness
    *   the new brightness of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateBrightness(newBrightness: Double): AquariumState

  /** Set PH to a given value
    * @param newPh
    *   the new PH of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updatePh(newPh: Double): AquariumState

  /** Set oxygenation to a given value
    * @param newOxygenation
    *   the new oxygenation of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateOxygenation(newOxygenation: Double): AquariumState

/** Companion object of [[UpdateAquariumState]] */
object UpdateAquariumState:

  /** Create a new [[UpdateAquariumState]] by a given [[AquariumState]]
    * @param aquariumState
    *   the [[AquariumState]] that have to be modified
    * @return
    *   a new [[UpdateAquariumState]]
    */
  def apply(aquariumState: AquariumState): UpdateAquariumState =
    UpdateAquariumStateImpl(aquariumState)

  /** Hidden implementation of [[UpdateAquariumState]]
    * @param aquarium
    *   the [[Aquarium]] that have to be modified
    */
  private class UpdateAquariumStateImpl(aquariumState: AquariumState) extends UpdateAquariumState:
    import AquariumParametersLimits.*
    override def updateImpurity(newImpurity: Double): AquariumState =
      newImpurity match
        case i if i > IMPURITY_MAX || i < IMPURITY_MIN => aquariumState
        case _ => aquariumState.copy(impurity = newImpurity)

    override def updateTemperature(newTemperature: Double): AquariumState =
      newTemperature match
        case t if t > TEMPERATURE_MAX || t < TEMPERATURE_MIN => aquariumState
        case _ => aquariumState.copy(temperature = newTemperature)

    override def updateBrightness(newBrightness: Double): AquariumState =
      newBrightness match
        case b if b > BRIGHTNESS_MAX || b < BRIGHTNESS_MIN => aquariumState
        case _ => aquariumState.copy(brightness = newBrightness)

    override def updatePh(newPh: Double): AquariumState =
      newPh match
        case p if p > PH_MAX || p < PH_MIN => aquariumState
        case _ => aquariumState.copy(ph = newPh)

    override def updateOxygenation(newOxygenation: Double): AquariumState =
      newOxygenation match
        case o if o > OXYGENATION_MAX || o < OXYGENATION_MIN => aquariumState
        case _ => aquariumState.copy(oxygenation = newOxygenation)
