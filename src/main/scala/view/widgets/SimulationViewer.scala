package view.widgets

import scalafx.geometry.Insets
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.{Background, BackgroundFill, BorderPane}
import scalafx.scene.paint.{Color, LinearGradient, Stops}

import scala.util.Random

import model.aquarium.AquariumDimensions
import model.aquarium.Aquarium
import model.fish.Fish
import model.Algae

object SimulationViewer:

  private val preferredWidth: Int = 500
  private val preferredHeight: Int = 500

  private val canvas: Canvas = new Canvas:
    managed = true
    width = preferredWidth
    height = preferredHeight

  val canvasPane = new BorderPane:
    center = canvas
    background = new Background(
      Array(
        new BackgroundFill(
          new LinearGradient(
            endX = 0,
            stops = Stops(
              Color.rgb(0, 191, 255),
              Color.rgb(25, 25, 112)
            )
          ),
          // Color.rgb(171, 205, 239),
          null,
          null
        )
      )
    )

  private val gc = canvas.graphicsContext2D

  private val greenFish: Image = new Image("/img/green-fish.png")
  private val redFish: Image = new Image("/img/red-fish.png")
  private val algaeImage: Image = new Image("/img/seaweed.png")

  def renderSimulation(aquarium: Aquarium): Unit =
    gc.clearRect(0, 0, canvas.width.value, canvas.height.value)
    aquarium.population.herbivorous.foreach((fish: Fish) => drawFish(greenFish, fish.position))
    aquarium.population.carnivorous.foreach((fish: Fish) => drawFish(redFish, fish.position))
    aquarium.population.algae.foreach((a: Algae) => drawAlgae(a))

  private def drawAlgae(algae: Algae): Unit =
    val canvasCoordinate: (Double, Double) = mapToCanvasCoordinate(algae.position)
    gc.drawImage(algaeImage, canvasCoordinate._1, canvasCoordinate._2, 50, 50)

  private def drawFish(fishImage: Image, coordinate: (Double, Double)): Unit =
    val canvasCoordinate: (Double, Double) = mapToCanvasCoordinate(coordinate)
    gc.drawImage(fishImage, canvasCoordinate._1, canvasCoordinate._2, 50, 50)

  private def mapToCanvasCoordinate(position: (Double, Double)): (Double, Double) =
    (
      position._1 / AquariumDimensions.WIDTH * preferredWidth,
      position._2 / AquariumDimensions.HEIGHT * preferredHeight
    )
