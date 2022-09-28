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

trait OxygenationSlider extends BorderPane:
  def update(newValue: Number): Unit

object OxygenationSlider:

  def apply(): OxygenationSlider = OxygenationSliderImpl()

  private class OxygenationSliderImpl extends OxygenationSlider:
    margin = Insets(15)

    private val slider: Slider = new Slider:
      min = AquariumParametersLimits.OXYGENATION_MIN - 1
      max = AquariumParametersLimits.OXYGENATION_MAX
      value = InitializeAquarium.OXYGENATION
      tooltip = SliderUtils.getTooltip(this.getValue, "mg/L")
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
    slider.valueProperty.addListener((_, oldVal: Number, newVal: Number) =>
      if oldVal != newVal then context.controller.updateOxygenation(newVal.doubleValue())
    )

    left = IconLabel("/icons/oxygen.png", "Aquarium oxygenation")
    right = slider

    def update(newValue: Number): Unit =
      // println("changing OxgenationSlider to " + newValue)
      slider.value = newValue.asInstanceOf[Double]
      slider.tooltip = SliderUtils.getTooltip(newValue, "mg/L")
