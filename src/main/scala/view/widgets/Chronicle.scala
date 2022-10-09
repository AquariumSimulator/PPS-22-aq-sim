package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Label, ListView}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import view.utils.AquariumFonts
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context
import mvc.ViewModule.ViewRequirements

object Chronicle:

  var list: ListView[String] = new ListView(
    items = Seq()
  )
  list.editable = true
  list.maxHeight = 150

  val chronicle = new BorderPane:
    margin = Insets(5, 0, 0, 0)
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    top = new Label:
      alignmentInParent = Pos.Center
      text = "Chronicle"
      font = AquariumFonts.bold(15.0)
    center = list

  def update(context: ViewRequirements): Unit =
    list = new ListView(
      items = context.controller.getCurrentChronicle.events.reverse
    )
    list.editable = true
    list.maxHeight = 150
    chronicle.center = list