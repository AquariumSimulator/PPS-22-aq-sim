package model.aquarium

/** Trait that models methods for modifying aquarium parameters */
trait UpdateAquariumState:

  /** Sets the impurity to a given value
    * @param newImpurity
    *   the new impurity level of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateImpurity(newImpurity: Double): AquariumState

  /** Sets the temperature to a given value
    * @param newTemperature
    *   the new temperature of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateTemperature(newTemperature: Double): AquariumState

  /** Sets the brightness to a given value
    * @param newBrightness
    *   the new brightness of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateBrightness(newBrightness: Double): AquariumState

  /** Sets PH to a given value
    * @param newPh
    *   the new PH of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updatePh(newPh: Double): AquariumState

  /** Sets oxygenation to a given value
    * @param newOxygenation
    *   the new oxygenation of the aquarium
    * @return
    *   a new [[AquariumState]]
    */
  def updateOxygenation(newOxygenation: Double): AquariumState
