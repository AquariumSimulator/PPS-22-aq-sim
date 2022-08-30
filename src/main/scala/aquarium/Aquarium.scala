package aquarium

/** Constants that represent the dimensions of the aquarium */
object Aquarium:

  /** Width of the aquarium */
  val WIDTH: Int = 200

  /** Height of the aquarium */
  val HEIGHT: Int = 150

/** Constants that represent the values assigned to a new simulation when it's created */
object InitializeAquarium:
  /** Initial temperature value (expressed in C°) */
  val TEMPERATURE: Int = 5

  /** Initial brightness value (expressed in %) */
  val BRIGHTNESS: Int = 50

  /** Initial PH value */
  val PH: Double = 7

  /** Initial impurity value (expressed in %) */
  val IMPURITY: Int = 10

  /** Initial oxygenation value (expressed in mg/L) */
  val OXYGENATION: Int = 10

/** Constants that represents the limit values of the various parameters of the aquarium */
object AquariumParametersLimits:

  /** Minimum value of the temperature (expressed in C°) */
  val TEMPERATURE_MIN: Int = 1
  /** Maximus value of the temperature (expressed in C°) */
  val TEMPERATURE_MAX: Int = 30

  /** Minimum value of the brightness (expressed in %) */
  val BRIGHTNESS_MIN: Int = 0
  /** Maximus value of the brightness (expressed in %) */
  val BRIGHTNESS_MAX: Int = 100

  /** Minimum value of the PH */
  val PH_MIN: Double = 0
  /** Maximus value of the PH */
  val PH_MAX: Double = 14

  /** Minimum value of the impurity (expressed in %) */
  val IMPURITY_MIN: Int = 0
  /** Maximus value of the impurity (expressed in %) */
  val IMPURITY_MAX: Int = 100

  /** Minimum value of the oxygenation (expressed in mg/L) */
  val OXYGENATION_MIN: Int = 0
  /** Maximus value of the oxygenation (expressed in mg/L) */
  val OXYGENATION_MAX: Int = 20
