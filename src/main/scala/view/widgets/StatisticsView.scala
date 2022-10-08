package view.widgets

import scalafx.collections.ObservableBuffer
import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, TilePane}
import scalafx.scene.control.{CheckBox, Label}
import scalafx.scene.chart.{LineChart, NumberAxis, XYChart}
import scalafx.geometry.{Insets, Pos}
import scalafx.Includes.jfxXYChartSeries2sfx
import view.utils.AquariumFonts
import mvc.MVC.given_ViewRequirements as context
import scalafx.application.Platform
import scalafx.beans.property.ObjectProperty

class StatisticsView extends Stage:

  private val populationTrend: List[(Int, Int, Int)] = context.controller.getPopulationTrend()

  private val herbivorousSeries: XYChart.Series[Number, Number] = XYChart.Series[Number, Number](
    "Herbivorous",
    ObservableBuffer().addAll(
      populationTrend.zipWithIndex.map((value, idx) => XYChart.Data[Number, Number](idx, value._1)).toList
    )
  )

  private val carnivorousSeries: XYChart.Series[Number, Number] = XYChart.Series[Number, Number](
    "Carnivorous",
    ObservableBuffer().addAll(
      populationTrend.zipWithIndex.map((value, idx) => XYChart.Data[Number, Number](idx, value._2)).toList
    )
  )

  private val algaeSeries: XYChart.Series[Number, Number] = XYChart.Series[Number, Number](
    "Algae",
    ObservableBuffer().addAll(
      populationTrend.zipWithIndex.map((value, idx) => XYChart.Data[Number, Number](idx, value._3)).toList
    )
  )

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

        lineChart.getData.addAll(herbivorousSeries, carnivorousSeries, algaeSeries)
        center = lineChart

        private def getCheckBox(text: String, index: Int, series: XYChart.Series[Number, Number]): CheckBox =
          new CheckBox(text):
            selected = true
            selected.addListener((_, _, isNowSelected) =>
              if isNowSelected == true then
                if index <= lineChart.getData.size then lineChart.getData.add(index, series)
                else lineChart.getData.add(series)
              else lineChart.getData.remove(series)
            )

        bottom = new TilePane:
          children ++= Seq(
            getCheckBox("Herbivorous", 0, herbivorousSeries),
            getCheckBox("Carnivorous", 1, carnivorousSeries),
            getCheckBox("Algae", 2, algaeSeries)
          )
          padding = Insets(10)
          hgap = 10
  )
  title = "Population charts"
