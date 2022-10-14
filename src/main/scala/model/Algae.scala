package model

import model.aquarium.AquariumDimensions

/** Algae model definition.
  *
  * @param base
  *   Starting and definitive base position (Y will always be 0).
  * @param height
  *   Starting height (1 by default).
  */
case class Algae(base: Double = 0, height: Int = Algae.DEFAULT_HEIGHT) extends Entity:
  val position: (Double, Double) = (base, AquariumDimensions.HEIGHT)
  val size: (Double, Double) = (height, height)
  val oxygenShift: Double = Algae.OXYGEN_MULTIPLIER * size._1
  val impurityShift: Double = 0.0
  val phShift: Double = -size._1 / Algae.PH_DIVISOR

object Algae:
  val DEFAULT_HEIGHT: Int = 1
  val MAX_HEIGHT: Int = 30
  val NUTRITION_AMOUNT: Int = 1
  val OXYGEN_MULTIPLIER: Double = Math.pow(10, -5)
  val PH_DIVISOR: Double = Math.pow(10, 5)
  val LOWER_BRIGHTNESS_LEVEL: Int = 5
  val MAX_GROWTH: Int = 2
  val MIN_GROWTH: Int = 0
