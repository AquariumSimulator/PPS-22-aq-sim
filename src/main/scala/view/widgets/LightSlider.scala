package view.widgets

import scalafx.scene.layout.BorderPane
import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control.Slider

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
    orientation = Orientation.Vertical
