package simulationView.widgets

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
import simulationView.widgets.BottomBar
import simulationView.widgets.SimulationViewer

object SimulationViewer:
  val canvas = new Canvas:
    width = 600
    height = 400
    /*
    background = new Background(
      Array(
        new BackgroundFill(Color.rgb(171, 205, 239), null, null)
      )
    )
     */
    margin = Insets.apply(
      top = 30,
      right = 30,
      bottom = 30,
      left = 30
    )
