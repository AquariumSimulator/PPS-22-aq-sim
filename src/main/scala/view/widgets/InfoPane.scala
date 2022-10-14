package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, ChoiceDialog, Label, Tooltip}
import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import view.utils.{AquariumFonts, IconButton}
import model.aquarium.Aquarium
import scalafx.event.ActionEvent
import view.widgets.download.{DownloadCSV, DownloadJSON}

import java.io.File

trait InfoPane:
  def updateInfo(newAquarium: Aquarium): Unit

object InfoPane:

  private val statisticsButton: IconButton = IconButton("icons/chart.png")
  statisticsButton.tooltip = new Tooltip("View statistics")
  statisticsButton.onAction = _ => StatisticsView().showAndWait()

  private val path = System.getProperty("user.home") + File.separator + "Downloads" + File.separator
  private val downloadButton: IconButton = IconButton("icons/download.png")
  downloadButton.tooltip = new Tooltip("Download simulation data")
  downloadButton.onAction = _ =>
    val dialog: ChoiceDialog[String] = new ChoiceDialog(
      defaultChoice = "Download CSV",
      choices = Seq("Download CSV", "Download JSON")
    ):
      title = "Download CVS or JSON"
      headerText = "Choose the type"
    val choice: Option[String] = dialog.showAndWait()

    choice match
      case Some("Download CSV") => DownloadCSV(path)
      case Some("Download JSON") => DownloadJSON(path)
      case Some(_) | None => None

  private val herbivorousFishLabel: InfoCell = InfoCell("Herbivorous fish", 0, "fish")
  private val carnivorousFishLabel: InfoCell = InfoCell("Carnivorous fish", 0, "fish")
  private val algaeLabel: InfoCell = InfoCell("Algae", 0, "algae")
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
    carnivorousFishLabel.update(newAquarium.population.carnivorous.size)
    algaeLabel.update(newAquarium.population.algae.size)
    temperatureLabel.update(newAquarium.aquariumState.temperature)
    brightnessLabel.update(newAquarium.aquariumState.brightness)
    phLabel.update(newAquarium.aquariumState.ph)
    impurityLabel.update(newAquarium.aquariumState.impurity)
    oxygenationLabel.update(newAquarium.aquariumState.oxygenation)
