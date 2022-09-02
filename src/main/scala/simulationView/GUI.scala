package simulationView

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text
import scalafx.scene.effect.DropShadow
import scalafx.Includes.*
import scalafx.application.JFXApp
import scalafx.scene.layout.*
import scalafx.scene.canvas.*
import scalafx.geometry.*
import scalafx.scene.*
import scalafx.scene.control.*
import scalafx.scene.paint.Color.*
import scalafx.scene.paint.*
import scalafx.scene.text.TextAlignment
object GUI extends JFXApp3:

    override def start(): Unit =
        stage = new JFXApp3.PrimaryStage:
            title = "Aquarium simulator"
            scene = new Scene:
                root = new BorderPane:
                    background = new Background(Array(new BackgroundFill(Color.Black, null, null)))
                    padding = Insets(10)
                    top = new BorderPane :
                        right = new BorderPane :
                            top = new BorderPane :
                                background = new Background(Array(new BackgroundFill(Color.White, null, null)))
                                center = new Label :
                                    text = "Info"
                            bottom = new BorderPane :
                                background = new Background(Array(new BackgroundFill(Color.White, null, null)))
                                center = new Label :
                                    text = "Chronicle"
                        left = new BorderPane :
                            top = new Label :
                                alignment = Pos.Center
                                text = "Aquarium Simulator"
                                textAlignment = TextAlignment.Center
                                style = "-fx-font: italic bold 15pt sans-serif"
                                textFill = Color.rgb(red = 10, green = 10, blue = 200)
                            left = new Slider :
                                min = 0
                                max = 100
                                value = 50
                                orientation = Orientation.Vertical
                            center = new BorderPane :
                                top = new BorderPane : //acquario
                                    center = new Canvas :
                                        width = 400
                                        height = 400
                                center = new BorderPane :
                                    left = new Slider :
                                        min = 0
                                        max = 100
                                        value = 50
                                    right = new Slider :
                                        min = 0
                                        max = 100
                                        value = 50

        stage.setFullScreen(false)