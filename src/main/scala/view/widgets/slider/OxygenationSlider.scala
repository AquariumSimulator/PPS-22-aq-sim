package view.widgets.slider

import javafx.scene.input.MouseEvent
import model.aquarium.{AquariumParametersLimits, InitializeAquarium}
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context
import mvc.ViewModule.ViewRequirements
import scalafx.geometry.Insets
import scalafx.scene.control.{Slider, Tooltip}
import scalafx.scene.layout.{Background, BackgroundFill, BorderPane, TilePane}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import view.utils.IconLabel
import view.widgets.slider.SliderUtils

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

    slider.valueProperty.addListener((_, _, newVal: Number) =>
      if slider.isPressed &&
        Math.abs(context.controller.getAquarium.aquariumState.oxygenation - newVal.doubleValue()) > 0.1
      then context.controller.updateOxygenation(newVal.doubleValue())
    )

    left = IconLabel("/icons/oxygen.png", "Aquarium oxygenation")
    right = slider

    def update(newValue: Number): Unit =
      if !slider.isHover then
        slider.value = newValue.doubleValue()
        slider.tooltip = SliderUtils.getTooltip(newValue, "mg/L")
