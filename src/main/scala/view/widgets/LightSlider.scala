package view.widgets

import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control.Slider
import scalafx.scene.layout.BorderPane
import view.utils.IconLabel

class LightSlider extends BorderPane:
  margin = Insets.apply(
    top = 0,
    right = 15,
    bottom = 5,
    left = 5
  )
  top = new IconLabel("/light.png")
  center = new Slider:
    min = 0
    max = 100
    value = 50
    style = "-fx-background-color: linear-gradient(to bottom, yellow, black)"
    orientation = Orientation.Vertical
