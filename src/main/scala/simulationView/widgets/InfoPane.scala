package simulationView.widgets

import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import scalafx.scene.control.Label
import scalafx.scene.control.Button
import scalafx.geometry.Pos
import scalafx.scene.text.Font
import scalafx.scene.text.FontWeight
import scalafx.scene.text.FontPosture

object InfoPane:
  val pane = new BorderPane:
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    center = new TilePane:
      alignmentInParent = Pos.Center
      alignment = Pos.Center
      children ++= Seq(
        new IconButton("/chart.png"):
          alignmentInParent = Pos.CenterLeft
        ,
        new Label:
          alignmentInParent = Pos.Center
          text = "Info"
          font = Font.font("Helvetica", FontWeight.Bold, FontPosture.Regular, 15.0)
        ,
        new IconButton("/download.png"):
          alignmentInParent = Pos.CenterRight
      )
    bottom = new GridPane:
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
