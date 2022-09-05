package model.aquarium

/** Trait that models methods for modifying aquarium parameters */
trait UpdateAquariumState:

  /** Clean the aquarium. When called the impurity level of the aquarium is set to 0
    * @return
    *   a new [[AquariumState]]
    */
  def clean(): AquariumState

  /** Set the temperature to a given value
    * @param newTemperature
    *   the new temperature of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateTemperature(newTemperature: Int): AquariumState

  /** Set the brightness to a given value
    * @param newBrightness
    *   the new brightness of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateBrightness(newBrightness: Int): AquariumState

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
  def updateOxygenation(newOxygenation: Int): AquariumState

/** Companion object of [[UpdateAquariumState]] */
object UpdateAquariumState:

  /** Create a new [[UpdateAquariumState]] by a given [[AquariumState]]
    * @param aquariumState
    *   the [[AquariumState]] that have to be modified
    * @return
    *   a new [[UpdateAquariumState]]
    */
  def apply(aquarium: Aquarium): UpdateAquariumState =
    UpdateAquariumStateImpl(aquarium)

  /** Hidden implementation of [[UpdateAquariumState]]
    * @param aquarium
    *   the [[Aquarium]] that have to be modified
    */
  private class UpdateAquariumStateImpl(aquarium: Aquarium) extends UpdateAquariumState:

    override def clean(): AquariumState =
      aquarium.aquariumState.copy(impurity = 0)

    override def updateTemperature(newTemperature: Int): AquariumState =
      aquarium.aquariumState.copy(temperature = newTemperature)

    override def updateBrightness(newBrightness: Int): AquariumState =
      aquarium.aquariumState.copy(brightness = newBrightness)

    override def updatePh(newPh: Double): AquariumState =
      aquarium.aquariumState.copy(ph = newPh)

    override def updateOxygenation(newOxygenation: Int): AquariumState =
      aquarium.aquariumState.copy(oxygenation = newOxygenation)
