package simulationView.widgets

import scalafx.scene.layout.TilePane
import scalafx.scene.control.Label
import scalafx.geometry.Pos

class InfoCell(val title: String, var content: String) extends TilePane:
  children ++= Seq(
    new Label:
      alignmentInParent = Pos.TopCenter
      text = title
    ,
    new Label:
      alignmentInParent = Pos.BottomCenter
      text = content
  )
