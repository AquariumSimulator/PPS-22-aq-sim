package model.aquarium

/** Case class that represent the current state of the aquarium
  * @param temperature
  *   temperature of the aquarium
  * @param brightness
  *   brightness of the aquarium
  * @param ph
  *   ph of the aquarium
  * @param impurity
  *   impurity of the aquarium
  * @param oxygenation
  *   oxygenation of the aquarium
  */
case class AquariumState(temperature: Int, brightness: Int, ph: Double, impurity: Int, oxygenation: Int)

/** Trait that models methods for modifying aquarium parameters */
trait UpdateAquariumState:

  /** Clean the aquarium. When called the impurity level of the aquarium is set to 0
    * @return
    *   a new [[Aquarium]]
    */
  def clean(): Aquarium

  /** Set the temperature to a given value
    * @param newTemperature
    *   the new temperature of the aquarium
    * @return
    *   a new [[Aquarium]]
    */
  def updateTemperature(newTemperature: Int): Aquarium

  /** Set the brightness to a given value
    * @param newBrightness
    *   the new brightness of the aquarium
    * @return
    *   a new [[Aquarium]]
    */
  def updateBrightness(newBrightness: Int): Aquarium

  /** Set PH to a given value
    * @param newPh
    *   the new PH of the aquarium
    * @return
    *   a new [[Aquarium]]
    */
  def updatePh(newPh: Double): Aquarium

  /** Set oxygenation to a given value
    * @param newOxygenation
    *   the new oxygenation of the aquarium
    * @return
    *   a new [[Aquarium]]
    */
  def updateOxygenation(newOxygenation: Int): Aquarium

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

    override def clean(): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.copy(impurity = 0))

    override def updateTemperature(newTemperature: Int): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.copy(temperature = newTemperature))

    override def updateBrightness(newBrightness: Int): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.copy(brightness = newBrightness))

    override def updatePh(newPh: Double): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.copy(ph = newPh))

    override def updateOxygenation(newOxygenation: Int): Aquarium =
      aquarium.copy(aquariumState = aquarium.aquariumState.copy(oxygenation = newOxygenation))
