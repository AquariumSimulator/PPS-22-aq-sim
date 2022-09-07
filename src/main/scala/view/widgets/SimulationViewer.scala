package view.widgets

import scalafx.scene.layout.{Background, BackgroundFill}
import scalafx.scene.paint.{LinearGradient, Color, Stops}
import scalafx.geometry.Insets
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.BorderPane
import scalafx.scene.image.Image
import scala.util.Random

object SimulationViewer:
  val canvas = new BorderPane:
    val canv = new Canvas:
      width = 600
      height = 400
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
      margin = Insets.apply(
        top = 30,
        right = 30,
        bottom = 30,
        left = 30
      )
    center = canv

    val gc = canv.graphicsContext2D

    val greenFish = new Image("/green-fish.png")
    val redFish = new Image("/red-fish.png")

    val rnd = new Random()
    for (n <- 1 to 10) gc.drawImage(greenFish, rnd.nextDouble() * 500, rnd.nextDouble() * 400, 60, 50)
    for (n <- 1 to 10) gc.drawImage(redFish, rnd.nextDouble() * 500, rnd.nextDouble() * 400, 60, 50)
