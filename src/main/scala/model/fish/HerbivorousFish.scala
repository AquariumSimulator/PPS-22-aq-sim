package model.fish

import model.HerbivorousFood
import model.fish.Fish.MAX_HUNGER

case class HerbivorousFish(
  override val speed: (Double, Double) = (0.0, 0.0),
  override val position: (Double, Double) = (0.0, 0.0),
  override val hunger: Int = MAX_HUNGER
) extends Fish