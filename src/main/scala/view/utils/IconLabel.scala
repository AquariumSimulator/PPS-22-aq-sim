package view.utils

import scalafx.scene.control.Label
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image

class IconLabel(val iconPath: String) extends Label:
  graphic = new ImageView:
    fitHeight = 20
    fitWidth = 20
    image = new Image(iconPath)
