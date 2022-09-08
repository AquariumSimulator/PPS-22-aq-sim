package view.utils

import scalafx.scene.control.Button
import scalafx.scene.image.{Image, ImageView}

class IconButton(val iconPath: String, val buttonHeight: Int = 20, val buttonWidth: Int = 20) extends Button:
  graphic = new ImageView:
    fitHeight = buttonHeight
    fitWidth = buttonWidth
    image = new Image(iconPath)
