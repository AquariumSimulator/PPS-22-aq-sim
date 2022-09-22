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

trait OxygenationSlider extends TilePane:
  def update(newValue: Double): Unit

object OxygenationSlider:

  def apply(): OxygenationSlider = OxygenationSliderImpl()

  private class OxygenationSliderImpl extends OxygenationSlider:
    margin = Insets(15)

    private val slider: Slider = new Slider:
      min = 0
      max = 50
      value = 12
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
      println("Changed oxygenation from " + oldVal + " to " + newVal)
      slider.tooltip = SliderUtils.getTooltip(newVal, "mg/L")
    )

    children ++= Seq(
      IconLabel("/icons/oxygen.png", "Aquarium oxygenation"),
      slider
    )

    def update(newValue: Double): Unit = println("updating oxygenslider to " + newValue)
