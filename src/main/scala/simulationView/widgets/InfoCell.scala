package simulationView.widgets

import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import scalafx.geometry.Pos
import scalafx.scene.text.FontWeight
import scalafx.scene.text.Font
import scalafx.scene.text.FontPosture

class InfoCell(val title: String, val initial_value: Double, val unit: String) extends BorderPane:
  top = new Label:
    alignmentInParent = Pos.Center
    text = title
    font = Font.font("Helvetica", FontWeight.Bold, FontPosture.Regular, 10.0)

  val bottomLabel: Label = new Label:
    alignmentInParent = Pos.Center
    font = Font.font("Helvetica", FontWeight.Normal, FontPosture.Regular, 15.0)

  bottom = bottomLabel

  def update(new_value: Double): Unit =
    bottomLabel.text = new_value.toString + " " + unit

  update(initial_value)
