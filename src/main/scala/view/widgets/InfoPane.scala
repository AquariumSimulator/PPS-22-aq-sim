package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label, Tooltip}
import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import view.utils.{AquariumFonts, IconButton}
import scalafx.event.ActionEvent
import scalafx.Includes._

object InfoPane:

  val statisticsButton: IconButton = IconButton("/icons/chart.png")
  statisticsButton.tooltip = new Tooltip("View statistics")
  statisticsButton.onAction = (event: ActionEvent) => println("Clicked view statistics")

  val downloadButton: IconButton = IconButton("/icons/download.png")
  downloadButton.tooltip = new Tooltip("Download simulation data")
  downloadButton.onAction = (event: ActionEvent) => println("Clicked download data")

  val pane = new BorderPane:
    padding = Insets(10, 10, 10, 10)
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    top = new BorderPane:
      left = statisticsButton
      center = new Label:
        text = "Info"
        font = AquariumFonts.bold(15.0)
      right = downloadButton
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
