package view

import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.geometry.*
import scalafx.scene.*
import scalafx.scene.canvas.*
import scalafx.scene.control.*
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.*
import scalafx.scene.paint.*
import scalafx.scene.paint.Color.*
import scalafx.scene.text.{Text, TextAlignment}
import scalafx.stage.Screen;

import view.utils.{AquariumFonts, IconLabel}
import view.widgets.*

object GUI extends JFXApp3:
  override def start(): Unit =

    println("Screen size: " + Screen.primary.bounds.width + "x" + Screen.primary.bounds.height)

    val preferredHeight: Double = Screen.primary.bounds.height * 3 / 4
    val preferredWidth: Double = Screen.primary.bounds.width * 3 / 4

    stage = new JFXApp3.PrimaryStage:
      title = "Aquarium Simulator"
      // height = preferredHeight
      // width = preferredWidth
      resizable = false
      scene = new Scene:
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

    stage.setFullScreen(false)
