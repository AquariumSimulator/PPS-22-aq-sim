package simulationView.widgets

import scalafx.scene.layout.*
import scalafx.scene.paint.Color
import scalafx.scene.control.Label

object Info:
	val info = new BorderPane:
		background = new Background(Array(new BackgroundFill(Color.White, null, null)))
		center = new Label:
			text = "Info"