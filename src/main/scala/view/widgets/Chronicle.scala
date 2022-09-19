package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Label, ListView}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import view.utils.AquariumFonts

object Chronicle:

  val list: ListView[String] = new ListView(
    items = Seq(
      "Fish1 is dead",
      "Fish2 ate Fish3",
      "Fish4 was born",
      "Fish2 ate Fish3"
    )
  )
  list.editable = false
  list.maxHeight = 150

  val chronicle = new BorderPane:
    margin = Insets(5, 0, 0, 0)
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    top = new Label:
      alignmentInParent = Pos.Center
      text = "Chronicle"
      font = AquariumFonts.bold(15.0)
    center = list
