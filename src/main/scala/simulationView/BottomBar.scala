package simulationView

import scalafx.scene.layout.*
import scalafx.scene.control.Button
import scalafx.geometry.Pos
import scalafx.geometry.Insets

import scala.language.postfixOps

object BottomBar:
    val buttonsNumber = 5

    val bottomBar = new TilePane:
        for (a <- 1 to buttonsNumber)
            children += new Button:
                text = a.toString
                margin = Insets.apply(
                    top = 10,
                    right = 10,
                    bottom = 10,
                    left = 10
                )

    bottomBar.alignment = Pos.Center