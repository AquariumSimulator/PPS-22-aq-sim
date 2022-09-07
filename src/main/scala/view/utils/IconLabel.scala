package view.utils

import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}

class IconLabel(val iconPath: String) extends Label:
  graphic = new ImageView:
    fitHeight = 30
    fitWidth = 30
    image = new Image(iconPath)
