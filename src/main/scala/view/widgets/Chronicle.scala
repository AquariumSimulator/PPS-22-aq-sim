package view.widgets

import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import scalafx.scene.control.Label
import scalafx.scene.control.ListView
import scalafx.geometry.Pos
import scalafx.geometry.Insets

import view.utils.AquariumFonts

object Chronicle:
  val chronicle = new BorderPane:
    margin = Insets.apply(
      top = 5,
      right = 0,
      bottom = 0,
      left = 0
    )
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    top = new Label:
      alignmentInParent = Pos.Center
      text = "Chronicle"
      font = AquariumFonts.bold(15.0)
    center = new ListView(
      items = Seq(
        "Fish1 is dead", "Fish2 ate Fish3", "Fish4 was born", "Fish2 ate Fish3", "Fish4 was born", "Fish2 ate Fish3",
        "Fish4 was born", "Fish2 ate Fish3", "Fish4 was born", "Fish2 ate Fish3", "Fish4 was born", "Fish2 ate Fish3",
        "Fish4 was born", "Fish2 ate Fish3", "Fish4 was born", "Fish2 ate Fish3", "Fish4 was born", "Fish2 ate Fish3",
        "Fish4 was born"
      )
    )
