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

class OxygenSlider extends BorderPane:
  margin = Insets.apply(
    top = 15,
    right = 15,
    bottom = 10,
    left = 15
  )
  left = new IconLabel("/oxygen.png")
  right = new Slider:
    min = 0
    max = 50
    value = 12
    background = new Background(
      Array(
        new BackgroundFill(
          new LinearGradient(
            proportional = true,
            stops = Stops(
              Color.White,
              Color.Grey
            )
          ),
          null,
          null
        )
      )
    )
