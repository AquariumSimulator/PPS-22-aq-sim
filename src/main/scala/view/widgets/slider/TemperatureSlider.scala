package view.widgets.slider

import model.aquarium.{AquariumParametersLimits, InitializeAquarium}
import scalafx.geometry.Insets
import scalafx.scene.control.{Slider, Tooltip}
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.paint.Color
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context
import mvc.ViewModule.ViewRequirements
import view.utils.IconLabel
import view.widgets.slider.SliderUtils
import scalafx.scene.layout.TilePane

import scala.language.postfixOps

trait TemperatureSlider extends BorderPane:
  def update(newValue: Number): Unit

object TemperatureSlider:

  def apply(): TemperatureSlider = TemperatureSliderImpl()

  private class TemperatureSliderImpl extends TemperatureSlider:
    margin = Insets(15)

    private val slider: Slider = new Slider:
      min = AquariumParametersLimits.TEMPERATURE_MIN - 1
      max = AquariumParametersLimits.TEMPERATURE_MAX
      value = InitializeAquarium.TEMPERATURE
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
      println("user asked to change temperature")
      context.controller.updateTemperature(newVal.doubleValue())
    )

    left = IconLabel("/icons/temperature.png", "Aquarium temperature")
    right = slider

    def update(newValue: Number): Unit =
      // println("changing TemperatureSlider to " + newValue)
      slider.value = newValue.asInstanceOf[Double]
      slider.tooltip = SliderUtils.getTooltip(newValue, "°")
