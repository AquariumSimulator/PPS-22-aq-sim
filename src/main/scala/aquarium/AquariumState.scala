package aquarium

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
