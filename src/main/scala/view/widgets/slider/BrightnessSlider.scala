package view.widgets.slider

import model.aquarium.{AquariumParametersLimits, InitializeAquarium}
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
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context
import mvc.ViewModule.ViewRequirements

import scala.language.postfixOps

trait BrightnessSlider extends BorderPane:
  def update(newValue: Number): Unit

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
      min = AquariumParametersLimits.BRIGHTNESS_MIN - 1
      max = AquariumParametersLimits.BRIGHTNESS_MAX
      value = InitializeAquarium.BRIGHTNESS
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
      // println("user asked to change brightness -> " + newVal)
      context.controller.updateBrightness(newVal.doubleValue())
    )

    center = slider

    def update(newValue: Number): Unit =
      // println("changing BrightnessSlider to " + newValue)
      slider.value = newValue.asInstanceOf[Double]
      slider.tooltip = SliderUtils.getTooltip(newValue, "%")
