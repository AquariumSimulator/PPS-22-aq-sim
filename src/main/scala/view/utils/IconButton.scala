package view.utils

import scalafx.scene.control.Button
import scalafx.scene.image.{Image, ImageView}

object IconButton:
  def setImage(button: IconButton, iconPath: String, buttonHeight: Int = 20, buttonWidth: Int = 20): Unit =
    button.graphic = new ImageView:
      fitHeight = buttonHeight
      fitWidth = buttonWidth
      image = new Image(iconPath)

class IconButton(val iconPath: String, val buttonHeight: Int = 20, val buttonWidth: Int = 20) extends Button:
  IconButton.setImage(this, iconPath, buttonHeight, buttonWidth)
