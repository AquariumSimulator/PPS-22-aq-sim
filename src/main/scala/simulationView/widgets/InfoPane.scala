package simulationView.widgets

import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import scalafx.scene.control.Label
import scalafx.scene.control.Button
import scalafx.geometry.Pos

object InfoPane:
  val pane = new BorderPane:
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    center = new TilePane:
      alignmentInParent = Pos.Center
      alignment = Pos.Center
      children ++= Seq(
        new Button:
          alignmentInParent = Pos.TopLeft
          text = "G"
        ,
        new Label:
          alignmentInParent = Pos.TopCenter
          text = "Info"
        ,
        new Button:
          alignmentInParent = Pos.TopCenter
          text = "D"
      )
    bottom = new GridPane:
      addRow(
        0,
        new InfoCell("Population", "0"),
        new InfoCell("Temperature", "25°")
      )
      addRow(
        1,
        new InfoCell("Brightness", "50%"),
        new InfoCell("pH", "5.6")
      )
      addRow(
        2,
        new InfoCell("Impurity", "20%"),
        new InfoCell("Oxygenation", "12 mg/L")
      )
