package view.widgets.slider

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
import scalafx.scene.layout.TilePane

trait TemperatureSlider extends TilePane:
  def update(newValue: Double): Unit

object TemperatureSlider:

  def apply(): TemperatureSlider = TemperatureSliderImpl()

  private class TemperatureSliderImpl extends TemperatureSlider:
    margin = Insets(15)

    private val slider: Slider = new Slider:
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

    children ++= Seq(
      IconLabel("/icons/temperature.png", "Aquarium temperature"),
      slider
    )

    def update(newValue: Double): Unit = println("updating temperatureslider to " + newValue)
