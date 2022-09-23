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

object GUI:
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
            left = new LightSlider
            center = new BorderPane:
              top = SimulationViewer.canvasPane
              bottom = new TilePane:
                left = new TemperatureSlider
                right = new OxygenSlider
            right = new BorderPane:
              margin = Insets(10, 10, 10, 10)
              top = InfoPane.pane
              bottom = Chronicle.chronicle
    )

    stage.setFullScreen(false)