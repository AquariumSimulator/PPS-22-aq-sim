package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label, Tooltip}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import view.utils.{AquariumFonts, IconButton}
import scalafx.event.ActionEvent
import scalafx.Includes._

import model.aquarium.Aquarium

trait InfoPane:
  def updateInfo(newAquarium: Aquarium): Unit

object InfoPane:

  private val statisticsButton: IconButton = IconButton("icons/chart.png")
  statisticsButton.tooltip = new Tooltip("View statistics")
  statisticsButton.onAction = (event: ActionEvent) => println("Clicked view statistics")

  private val downloadButton: IconButton = IconButton("icons/download.png")
  downloadButton.tooltip = new Tooltip("Download simulation data")
  downloadButton.onAction = (event: ActionEvent) => println("Clicked download data")

  private val herbivorousFishLabel: InfoCell = InfoCell("Herbivorous fish", 0, "fish")
  private val carnivorousFishLabel: InfoCell = InfoCell("Carnivorous fish", 0, "fish")
  private val algaeLabel: InfoCell = InfoCell("Algae", 0, "fish")
  private val temperatureLabel: InfoCell = InfoCell("Temperature", 25, "°")
  private val brightnessLabel: InfoCell = InfoCell("Brightness", 50, "%")
  private val phLabel: InfoCell = InfoCell("pH", 5.6, "")
  private val impurityLabel: InfoCell = InfoCell("Impurity", 20, "%")
  private val oxygenationLabel: InfoCell = InfoCell("Oxygenation", 12, "mg/L")

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
        herbivorousFishLabel,
        carnivorousFishLabel
      )
      addRow(
        1,
        algaeLabel,
        temperatureLabel
      )
      addRow(
        2,
        brightnessLabel,
        phLabel
      )
      addRow(
        3,
        impurityLabel,
        oxygenationLabel
      )

  def updateInfo(newAquarium: Aquarium): Unit =
    herbivorousFishLabel.update(newAquarium.population.herbivorous.size)
    herbivorousFishLabel.update(newAquarium.population.carnivorous.size)
    herbivorousFishLabel.update(newAquarium.population.algae.size)
    temperatureLabel.update(newAquarium.aquariumState.temperature)
    brightnessLabel.update(newAquarium.aquariumState.brightness)
    phLabel.update(newAquarium.aquariumState.ph)
    impurityLabel.update(newAquarium.aquariumState.impurity)
    oxygenationLabel.update(newAquarium.aquariumState.oxygenation)
