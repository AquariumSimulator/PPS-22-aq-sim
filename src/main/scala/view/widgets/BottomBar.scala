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
      BottomBarButton("/add-fish.png"),
      BottomBarButton("/remove-fish.png"),
      BottomBarButton("/play.png"),
      BottomBarButton("/food.png"),
      BottomBarButton("/clean.png")
    )

  bottomBar.alignment = Pos.Center
