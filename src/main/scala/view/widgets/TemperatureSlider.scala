package view.widgets

import scalafx.scene.layout.BorderPane
import scalafx.geometry.Insets
import scalafx.scene.control.Slider

import view.utils.IconLabel

class TemperatureSlider extends BorderPane:
  margin = Insets.apply(
    top = 15,
    right = 15,
    bottom = 10,
    left = 15
  )
  left = new IconLabel("/temperature.png")
  right = new Slider:
    min = 0
    max = 100
    value = 50
