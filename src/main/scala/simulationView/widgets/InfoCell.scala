package simulationView.widgets

import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import scalafx.geometry.Pos
import scalafx.scene.text.FontWeight
import scalafx.scene.text.Font
import scalafx.scene.text.FontPosture

class InfoCell(val title: String, val content: String) extends BorderPane:
  top = new Label:
    alignmentInParent = Pos.Center
    text = title
    font = Font.font("Helvetica", FontWeight.Bold, FontPosture.Regular, 10.0)
  bottom = new Label:
    alignmentInParent = Pos.Center
    text = content
    font = Font.font("Helvetica", FontWeight.Normal, FontPosture.Regular, 15.0)
