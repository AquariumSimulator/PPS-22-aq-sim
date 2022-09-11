package view.widgets

import scalafx.geometry.Insets
import scalafx.scene.control.{Slider, Tooltip}
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.paint.Color

import view.utils.IconLabel
import view.widgets.slider.SliderUtils

class TemperatureSlider extends BorderPane:
  margin = Insets.apply(
    top = 15,
    right = 15,
    bottom = 10,
    left = 15
  )
  left = new IconLabel("/temperature.png"):
    tooltip = new Tooltip("Aquarium temperature")

  val slider: Slider = new Slider:
    min = 0
    max = 30
    value = 25
    tooltip = SliderUtils.getTooltip(this.getValue, "°")
    background = new Background(
      Array(
        new BackgroundFill(
          new LinearGradient(
            stops = Stops(
              Color.Blue,
              Color.Red
            )
          ),
          null,
          null
        )
      )
    )
  slider.valueProperty.addListener((_, oldVal: Number, newVal: Number) =>
    println("Changed temperature from " + oldVal + " to " + newVal)
    slider.tooltip = SliderUtils.getTooltip(newVal, "°")
  )

  right = slider
