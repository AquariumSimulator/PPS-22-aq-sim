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
case class AquariumState(
    temperature: Double = InitializeAquarium.TEMPERATURE,
    brightness: Double = InitializeAquarium.BRIGHTNESS,
    ph: Double = InitializeAquarium.PH,
    impurity: Double = InitializeAquarium.IMPURITY,
    oxygenation: Double = InitializeAquarium.OXYGENATION
) extends UpdateAquariumState:

  import AquariumParametersLimits.*

  override def updateImpurity(newImpurity: Double): AquariumState =
    newImpurity match
      case i if i > IMPURITY_MAX || i < IMPURITY_MIN => this
      case _ => this.copy(impurity = newImpurity)

  override def updateTemperature(newTemperature: Double): AquariumState =
    newTemperature match
      case t if t > TEMPERATURE_MAX || t < TEMPERATURE_MIN => this
      case _ => this.copy(temperature = newTemperature)

  override def updateBrightness(newBrightness: Double): AquariumState =
    newBrightness match
      case b if b > BRIGHTNESS_MAX || b < BRIGHTNESS_MIN => this
      case _ => this.copy(brightness = newBrightness)

  override def updatePh(newPh: Double): AquariumState =
    newPh match
      case p if p > PH_MAX || p < PH_MIN => this
      case _ => this.copy(ph = newPh)

  override def updateOxygenation(newOxygenation: Double): AquariumState =
    newOxygenation match
      case o if o > OXYGENATION_MAX || o < OXYGENATION_MIN => this
      case _ => this.copy(oxygenation = newOxygenation)
