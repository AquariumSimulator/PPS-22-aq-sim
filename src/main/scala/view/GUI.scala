package view

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text
import scalafx.scene.effect.DropShadow
import scalafx.Includes.*
import scalafx.application.JFXApp
import scalafx.scene.layout.*
import scalafx.scene.canvas.*
import scalafx.geometry.*
import scalafx.scene.*
import scalafx.scene.control.*
import scalafx.scene.paint.Color.*
import scalafx.scene.paint.*
import scalafx.scene.text.TextAlignment

import view.widgets.*
import view.utils.{AquariumFonts, IconLabel}

object GUI extends JFXApp3:
  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      title = "Aquarium Simulator"
      scene = new Scene:
        root = new BorderPane:
          background = new Background(Array(new BackgroundFill(Color.White, null, null)))
          padding = Insets(10)
          bottom = BottomBar.bottomBar
          top = new BorderPane:
            margin = Insets.apply(
              top = 0,
              right = 5,
              bottom = 10,
              left = 5
            )
            right = new BorderPane:
              margin = Insets.apply(
                top = 10,
                right = 10,
                bottom = 10,
                left = 10
              )
              top = InfoPane.pane
              bottom = Chronicle.chronicle
            left = new BorderPane:
              top = new Label:
                alignmentInParent = Pos.Center
                alignment = Pos.Center
                text = "Aquarium Simulator"
                textAlignment = TextAlignment.Center
                font = AquariumFonts.bold(25.0)
                textFill = Color.rgb(0, 150, 255)
                margin = Insets.apply(
                  top = 0,
                  right = 5,
                  bottom = 15,
                  left = 5
                )
              left = new LightSlider
              center = new BorderPane:
                top = new BorderPane:
                  center = SimulationViewer.canvas
                bottom = new TilePane:
                  left = new TemperatureSlider
                  right = new BorderPane:
                    margin = Insets.apply(
                      top = 15,
                      right = 15,
                      bottom = 10,
                      left = 15
                    )
                    left = new IconLabel("/oxygen.png")
                    right = new Slider:
                      min = 0
                      max = 100
                      value = 50

    stage.setFullScreen(false)
