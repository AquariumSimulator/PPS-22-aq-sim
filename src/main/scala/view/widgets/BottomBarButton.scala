package view.widgets

import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.Color
import scalafx.geometry.Insets

import view.utils.IconButton

class BottomBarButton(override val iconPath: String) extends IconButton(iconPath):
  margin = Insets(10, 10, 10, 10)
  padding = Insets(10, 10, 10, 10)
  style = "-fx-background-radius: 30; -fx-background-color: yellow"
