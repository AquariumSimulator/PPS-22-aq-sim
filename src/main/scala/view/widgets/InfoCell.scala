package view.widgets

import scalafx.geometry.Pos
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
import view.utils.AquariumFonts

trait InfoCell extends BorderPane:
  def update(newValue: Double): Unit

object InfoCell:

  def apply(title: String, initialValue: Double, unit: String): InfoCell = InfoCellImpl(title, initialValue, unit)

  private class InfoCellImpl(val title: String, val initialValue: Double, val unit: String) extends InfoCell:
    top = new Label:
      alignmentInParent = Pos.Center
      text = title
      font = AquariumFonts.bold(10.0)

    val bottomLabel: Label = new Label:
      alignmentInParent = Pos.Center
      font = AquariumFonts.normal(15.0)

    bottom = bottomLabel

    def update(newValue: Double): Unit =
      bottomLabel.text = f"$newValue%3.1f " + unit

    update(initialValue)
