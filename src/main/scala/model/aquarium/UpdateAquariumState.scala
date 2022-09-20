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
