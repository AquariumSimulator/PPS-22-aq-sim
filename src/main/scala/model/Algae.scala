package model

/** Algae model definition.
  *
  * @param base
  *   Starting and definitive base position (Y will always be 0).
  * @param height
  *   Starting height (1 by default).
  */
case class Algae(base: Double, height: Int = Algae.DEFAULT_HEIGHT) extends Entity:
  val position: (Double, Double) = (base, 0)
  val oxygenShift: Double = Algae.OXYGEN_MULTIPLIER * height
  val impurityShift: Double = 0.0
  val phShift: Double = -height / Algae.PH_DIVISOR

object Algae:
  val DEFAULT_HEIGHT: Int = 1
  val MAX_HEIGHT: Int = 20
  val NUTRITION_AMOUNT: Int = 1
  val OXYGEN_MULTIPLIER: Double = 0.2
  val PH_DIVISOR: Double = 50
  val LOWER_BRIGHTNESS_LEVEL: Int = 5
  val MAX_GROWTH: Int = 10
