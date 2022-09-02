package simulationView.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.layout.{Background, BackgroundFill, TilePane}
import scalafx.scene.paint.Color

object BottomBar:
    val buttonsNumber = 5
    val bottomBar = new TilePane :
        background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
        for (a <- 1 to buttonsNumber)
            children += new Button :
                //shape = new Circle
                text = a.toString
                background = new Background(Array(new BackgroundFill(Color.Yellow, null, null)))
                margin = Insets.apply(
                    top = 10,
                    right = 10,
                    bottom = 10,
                    left = 10
                )

    bottomBar.alignment = Pos.Center
