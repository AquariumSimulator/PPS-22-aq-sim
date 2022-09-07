package view.utils

import scalafx.scene.control.Button
import scalafx.scene.image.{Image, ImageView}

class IconButton(val iconPath: String) extends Button:
  graphic = new ImageView:
    fitHeight = 20
    fitWidth = 20
    image = new Image(iconPath)
