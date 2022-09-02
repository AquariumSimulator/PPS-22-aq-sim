package simulationView

import scalafx.scene.layout.*
import scalafx.scene.control.Button

import scala.language.postfixOps

object BottomBar:
    val bottomBar = new TilePane:
        children += new Button("1")
        children += new Button("2")
        children += new Button("3")
        children += new Button("4")
        children += new Button("5")