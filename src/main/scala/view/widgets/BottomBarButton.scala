package view.widgets

import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.Color
import scalafx.geometry.Insets

import view.utils.IconButton

import BottomBarButton._

object BottomBarButton:
  val DEFAULT_WIDTH: Int = 30
  val DEFAULT_HEIGHT: Int = 30

class BottomBarButton(override val iconPath: String) extends IconButton(iconPath, DEFAULT_WIDTH, DEFAULT_HEIGHT):
  margin = Insets(20, 40, 20, 40)
  padding = Insets(10, 10, 10, 10)
  style = "-fx-background-radius: 30; -fx-background-color: yellow"
