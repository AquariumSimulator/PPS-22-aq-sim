package view.widgets

import scalafx.geometry.Insets
import scalafx.scene.control.Slider
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.paint.Color

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
    max = 30
    value = 25
    background = new Background(
      Array(
        new BackgroundFill(
          new LinearGradient(
            stops = Stops(
              Color.Red,
              Color.Blue
            )
          ),
          null,
          null
        )
      )
    )
