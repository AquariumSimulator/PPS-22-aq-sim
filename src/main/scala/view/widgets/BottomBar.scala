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
      new BottomBarButton("/add-fish.png"),
      new BottomBarButton("/remove-fish.png"),
      new BottomBarButton("/play.png"),
      new BottomBarButton("/food.png"),
      new BottomBarButton("/clean.png")
    )

  bottomBar.alignment = Pos.Center
