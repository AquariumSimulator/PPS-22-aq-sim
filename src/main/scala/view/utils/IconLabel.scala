package view.utils

import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}

class IconLabel(val iconPath: String) extends Label:
  graphic = new ImageView:
    fitHeight = 20
    fitWidth = 20
    image = new Image(iconPath)
