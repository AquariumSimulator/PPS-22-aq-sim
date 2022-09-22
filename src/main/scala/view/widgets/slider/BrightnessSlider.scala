package view.widgets.slider

import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control.{Slider, Tooltip}
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.paint.Color

import view.utils.IconLabel
import view.widgets.slider.SliderUtils

trait BrightnessSlider extends BorderPane:
  def update(newValue: Double): Unit

object BrightnessSlider:

  def apply(): BrightnessSlider = BrightnessSliderImpl()

  private class BrightnessSliderImpl extends BrightnessSlider:
    margin = Insets(
      top = 0,
      right = 15,
      bottom = 5,
      left = 5
    )
    top = IconLabel("/icons/light.png", "Aquarium brightness")

    private val slider: Slider = new Slider:
      min = 0
      max = 100
      value = 50
      tooltip = SliderUtils.getTooltip(this.getValue, "%")
      background = new Background(
        Array(
          new BackgroundFill(
            new LinearGradient(
              stops = Stops(
                Color.Yellow,
                Color.Black
              )
            ),
            null,
            null
          )
        )
      )
      orientation = Orientation.Vertical
    slider.valueProperty.addListener((_, oldVal: Number, newVal: Number) =>
      println("Changed brightness from " + oldVal + " to " + newVal)
      slider.tooltip = SliderUtils.getTooltip(newVal, "%")
    )

    center = slider

    def update(newValue: Double): Unit = println("updating lightslider to " + newValue)
