package view.widgets

import scalafx.collections.ObservableBuffer
import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, TilePane}
import scalafx.scene.control.{Label, CheckBox}
import scalafx.scene.chart.{LineChart, NumberAxis, XYChart}
import scalafx.geometry.{Pos, Insets}
import view.utils.AquariumFonts
import mvc.MVC.given_ViewRequirements as context

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

        val populationTrend: List[(Int, Int, Int)] = context.controller.getPopulationTrend()
        lineChart.getData
          .add(
            XYChart.Series[Number, Number](
              "Herbivorous",
              ObservableBuffer().addAll(
                populationTrend.zipWithIndex.map((value, idx) => XYChart.Data[Number, Number](idx, value._1)).toList
              )
            )
          )
        lineChart.getData.add(
          XYChart.Series[Number, Number](
            "Carnivorous",
            ObservableBuffer().addAll(
              populationTrend.zipWithIndex.map((value, idx) => XYChart.Data[Number, Number](idx, value._2)).toList
            )
          )
        )
        lineChart.getData.add(
          XYChart.Series[Number, Number](
            "Algae",
            ObservableBuffer().addAll(
              populationTrend.zipWithIndex.map((value, idx) => XYChart.Data[Number, Number](idx, value._3)).toList
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
