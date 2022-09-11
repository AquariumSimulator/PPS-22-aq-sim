package view.widgets

import scalafx.geometry.Insets
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.{Background, BackgroundFill, BorderPane}
import scalafx.scene.paint.{Color, LinearGradient, Stops}

import scala.util.Random

object SimulationViewer:
  val canvasPane = new BorderPane:
    val canvas = new Canvas:
      managed = true
      width = 600
      height = 600
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
            //Color.rgb(171, 205, 239),
            null,
            null
          )
        )
      )
      margin = Insets(30)

    center = canvas

    val gc = canvas.graphicsContext2D

    val greenFish = new Image("/green-fish.png")
    val redFish = new Image("/red-fish.png")

    val rnd = new Random()
    gc.drawImage(greenFish, 0, 0, 50, 50)
    for (n <- 1 to 10) gc.drawImage(greenFish, rnd.nextDouble() * 500, rnd.nextDouble() * 400, 60, 50)
    for (n <- 1 to 10) gc.drawImage(redFish, rnd.nextDouble() * 500, rnd.nextDouble() * 400, 60, 50)
