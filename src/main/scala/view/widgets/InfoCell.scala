package view.widgets

import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import scalafx.geometry.Pos

import view.utils.AquariumFonts

class InfoCell(val title: String, val initial_value: Double, val unit: String) extends BorderPane:
  top = new Label:
    alignmentInParent = Pos.Center
    text = title
    font = AquariumFonts.bold(10.0)

  val bottomLabel: Label = new Label:
    alignmentInParent = Pos.Center
    font = AquariumFonts.normal(15.0)

  bottom = bottomLabel

  def update(new_value: Double): Unit =
    bottomLabel.text = new_value.toString + " " + unit

  update(initial_value)
