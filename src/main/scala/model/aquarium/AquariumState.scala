package model.aquarium

/** Case class that represents the current state of the aquarium
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

  import AquariumParametersLimits._

  override def updateImpurity(newImpurity: Double): AquariumState =
    checkValueAndReturnAquarium(newImpurity)((i: Double) => i > IMPURITY_MAX || i < IMPURITY_MIN)(
      this.copy(impurity = newImpurity)
    )

  override def updateTemperature(newTemperature: Double): AquariumState =
    checkValueAndReturnAquarium(newTemperature)((t: Double) => t > TEMPERATURE_MAX || t < TEMPERATURE_MIN)(
      this.copy(temperature = newTemperature)
    )

  override def updateBrightness(newBrightness: Double): AquariumState =
    checkValueAndReturnAquarium(newBrightness)((b: Double) => b > BRIGHTNESS_MAX || b < BRIGHTNESS_MIN)(
      this.copy(brightness = newBrightness)
    )

  override def updatePh(newPh: Double): AquariumState =
    checkValueAndReturnAquarium(newPh)((p: Double) => p > PH_MAX || p < PH_MIN)(this.copy(ph = newPh))

  override def updateOxygenation(newOxygenation: Double): AquariumState =
    checkValueAndReturnAquarium(newOxygenation)((o: Double) => o > OXYGENATION_MAX || o < OXYGENATION_MIN)(
      this.copy(oxygenation = newOxygenation)
    )

  private def checkValueAndReturnAquarium(value: Double)(checkFunc: Double => Boolean)(
      newAquarium: AquariumState
  ): AquariumState =
    value match
      case v if checkFunc(v) => this
      case _ => newAquarium
