package view.widgets

import scalafx.collections.ObservableBuffer
import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, TilePane}
import scalafx.scene.control.{Label, CheckBox}
import scalafx.scene.chart.{LineChart, NumberAxis, XYChart}
import scalafx.geometry.{Pos, Insets}
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
        val lineChart: LineChart[Number, Number] =
          LineChart[Number, Number](NumberAxis("Iteration"), NumberAxis("Population"))
        lineChart.getData
          .add(
            XYChart.Series[Number, Number](
              "Herbivorous",
              ObservableBuffer(
                XYChart.Data[Number, Number](1, 23),
                XYChart.Data[Number, Number](2, 14),
                XYChart.Data[Number, Number](3, 17),
                XYChart.Data[Number, Number](4, 7)
              )
            )
          )
        lineChart.getData.add(
          XYChart.Series[Number, Number](
            "Carnivorous",
            ObservableBuffer(
              XYChart.Data[Number, Number](1, 18),
              XYChart.Data[Number, Number](2, 22),
              XYChart.Data[Number, Number](3, 15),
              XYChart.Data[Number, Number](4, 4)
            )
          )
        )
        lineChart.getData.add(
          XYChart.Series[Number, Number](
            "Algae",
            ObservableBuffer(
              XYChart.Data[Number, Number](1, 8),
              XYChart.Data[Number, Number](2, 17),
              XYChart.Data[Number, Number](3, 26),
              XYChart.Data[Number, Number](4, 30)
            )
          )
        )
        center = lineChart
        bottom = new TilePane:
          children ++= Seq(CheckBox("Herbivorous"), CheckBox("Carnivorous"), CheckBox("Algae"))
          padding = Insets(10)
          hgap = 10
  )
  title = "Population charts"
