package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import view.utils.{AquariumFonts, IconButton}

object InfoPane:
  val pane = new BorderPane:
    padding = Insets(10, 10, 10, 10)
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    top = new BorderPane:
      left = new IconButton("/chart.png")
      center = new Label:
        text = "Info"
        font = AquariumFonts.bold(15.0)
      right = new IconButton("/download.png")
    bottom = new GridPane:
      alignment = Pos.Center
      hgap = 40
      vgap = 10
      padding = Insets(20, 0, 10, 0)
      addRow(
        0,
        new InfoCell("Population", 0, "fish"),
        new InfoCell("Temperature", 25, "°")
      )
      addRow(
        1,
        new InfoCell("Brightness", 50, "%"),
        new InfoCell("pH", 5.6, "")
      )
      addRow(
        2,
        new InfoCell("Impurity", 20, "%"),
        new InfoCell("Oxygenation", 12, "mg/L")
      )
