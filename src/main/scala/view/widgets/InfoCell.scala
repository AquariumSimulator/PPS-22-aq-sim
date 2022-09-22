package view.widgets

import scalafx.geometry.Pos
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
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
    println("Updating " + title + " with " + f"$new_value%3.1f")
    bottomLabel.text = f"$new_value%3.1f " + unit

  update(initial_value)
