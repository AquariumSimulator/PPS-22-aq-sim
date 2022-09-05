package simulationView.widgets

import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import scalafx.scene.control.Label
import scalafx.scene.control.Button
import scalafx.geometry.Pos

object Info:
  val info = new BorderPane:
    background = new Background(Array(new BackgroundFill(Color.White, null, null)))
    center = new TilePane:
      alignmentInParent = Pos.Center
      alignment = Pos.Center
      children += new Button:
        alignmentInParent = Pos.TopLeft
        text = "G"
      children += new Label:
        alignmentInParent = Pos.TopCenter
        text = "Info"
      children += new Button:
        alignmentInParent = Pos.TopRight
        text = "D"
    bottom = new GridPane:
      add(
        new Button:
          text = "G"
        ,
        0,
        0
      )
      add(
        new Button:
          text = "A"
        ,
        1,
        1
      )
      add(
        new Button:
          text = "Y"
        ,
        0,
        2
      )
      add(
        new Button:
          text = "P"
        ,
        1,
        0
      )
      add(
        new Button:
          text = "E"
        ,
        0,
        1
      )
      add(
        new Button:
          text = "D"
        ,
        1,
        2
      )
