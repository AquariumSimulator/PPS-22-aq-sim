package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.{Background, BackgroundFill, TilePane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import view.utils.IconButton

object BottomBar:
  val bottomBar = new TilePane:
    background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
    children ++= Seq(
      new IconButton("/add-fish.png"):
        background = new Background(Array(new BackgroundFill(Color.Yellow, null, null)))
      ,
      new IconButton("/remove-fish.png"):
        background = new Background(Array(new BackgroundFill(Color.Yellow, null, null)))
      ,
      new IconButton("/play.png"):
        background = new Background(Array(new BackgroundFill(Color.Yellow, null, null)))
      ,
      new IconButton("/food.png"):
        background = new Background(Array(new BackgroundFill(Color.Yellow, null, null)))
      ,
      new IconButton("/clean.png"):
        background = new Background(Array(new BackgroundFill(Color.Yellow, null, null))),
    )

  bottomBar.alignment = Pos.Center
