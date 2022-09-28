package view

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.geometry._
import scalafx.scene._
import scalafx.scene.canvas._
import scalafx.scene.control._
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout._
import scalafx.scene.paint._
import scalafx.scene.paint.Color._
import scalafx.scene.text.{Text, TextAlignment}
import scalafx.stage.{Stage, Screen}

import view.utils.{AquariumFonts, IconLabel}
import view.widgets._
import view.widgets.slider._

trait GUI:
  def start(stage: Stage): Unit
  def updateSliders(temperature: Number, brightness: Number, oxygenation: Number): Unit

object GUI:

  private val brightnessSlider: BrightnessSlider = BrightnessSlider()
  private val temperatureSlider: TemperatureSlider = TemperatureSlider()
  private val oxygenationSlider: OxygenationSlider = OxygenationSlider()

  def start(stage: Stage): Unit =

    println("Screen size: " + Screen.primary.bounds.width + "x" + Screen.primary.bounds.height)

    val preferredHeight: Double = Screen.primary.bounds.height * 3 / 4
    val preferredWidth: Double = Screen.primary.bounds.width * 3 / 4

    stage.setTitle("Aquarium Simulator")
    stage.setResizable(false)
    stage.setScene(
      new Scene:
        root = new BorderPane:
          background = new Background(Array(new BackgroundFill(Color.Black, null, null)))
          padding = Insets(10)
          bottom = BottomBar.bottomBar
          top = new Label:
            alignmentInParent = Pos.Center
            alignment = Pos.Center
            text = "Aquarium Simulator"
            textAlignment = TextAlignment.Center
            font = AquariumFonts.bold(25.0)
            textFill = Color.rgb(0, 150, 255)
            margin = Insets(0, 5, 15, 5)
          center = new BorderPane:
            left = brightnessSlider
            center = new BorderPane:
              top = SimulationViewer.canvasPane
              bottom = new BorderPane:
                left = temperatureSlider
                right = oxygenationSlider
            right = new BorderPane:
              margin = Insets(10, 10, 10, 10)
              top = InfoPane.pane
              bottom = Chronicle.chronicle
    )

    stage.setFullScreen(false)

  def updateSliders( /*temperature: Number, brightness: Number, */ oxygenation: Number): Unit =
    // temperatureSlider.update(temperature)
    // brightnessSlider.update(brightness)
    oxygenationSlider.update(oxygenation)
