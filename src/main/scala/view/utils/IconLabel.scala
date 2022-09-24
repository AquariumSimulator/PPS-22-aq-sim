package view.utils

import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.control.Tooltip

object IconLabel:
  def apply(iconPath: String, tooltipMessage: String): Label = new Label:
    graphic = new ImageView:
      fitHeight = 30
      fitWidth = 30
      image = new Image(iconPath)
    tooltip = Tooltip(tooltipMessage)
