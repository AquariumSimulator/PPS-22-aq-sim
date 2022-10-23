package view.widgets.slider

import scalafx.scene.control.Tooltip

object SliderUtils:
  def getTooltip(value: Number, unit: String): Tooltip =
    val v: Double = value.asInstanceOf[Double].doubleValue
    new Tooltip(f"$v%3.1f " + unit)
