package view.widgets

import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, TilePane}
import scalafx.scene.control.{Label, CheckBox}
import scalafx.geometry.{Pos, Insets}
import scalafx.scene.chart.{LineChart, Axis}
import scalafx.scene.chart.XYChart
import view.utils.AquariumFonts

class StatisticsView extends Stage:
  this.setScene(
    new Scene:
      root = new BorderPane:
        top = new Label:
          text = "Population charts"
          font = AquariumFonts.bold(15)
          alignment = Pos.Center
          alignmentInParent = Pos.Center
          padding = Insets(15)
        val lineChart = new LineChart(new NumberAxis(), new NumberAxis()):
          padding = Insets(15)
        center = lineChart
        val series = new XYChart.Series()
        series.setName("My portfolio")

        series.getData().add(new XYChart.Data(1, 23))
        series.getData().add(new XYChart.Data(2, 14))
        series.getData().add(new XYChart.Data(3, 15))
        series.getData().add(new XYChart.Data(4, 24))
        series.getData().add(new XYChart.Data(5, 34))
        series.getData().add(new XYChart.Data(6, 36))
        series.getData().add(new XYChart.Data(7, 22))
        series.getData().add(new XYChart.Data(8, 45))
        series.getData().add(new XYChart.Data(9, 43))
        series.getData().add(new XYChart.Data(10, 17))
        series.getData().add(new XYChart.Data(11, 29))
        series.getData().add(new XYChart.Data(12, 25))
        lineChart.getData().add(series)
        bottom = new TilePane:
          children ++= Seq(CheckBox("Herbivorous"), CheckBox("Carnivorous"), CheckBox("Algae"))
          padding = Insets(10)
          hgap = 10
  )
  title = "Population charts"
