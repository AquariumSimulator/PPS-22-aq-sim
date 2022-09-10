package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, ChoiceDialog}
import scalafx.scene.layout.{Background, BackgroundFill, TilePane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.Includes._
import scalafx.event.ActionEvent
import view.utils.IconButton

object BottomBar:

  val addFishButton: BottomBarButton = BottomBarButton("/add-fish.png")
  addFishButton.onAction = (event: ActionEvent) =>
    val dialog = new ChoiceDialog:
      defaultChoice = "Herbivorous Fish"
      choices = Seq("Carnivorous Fish", "Herbivorous Fish", "Algae")
      headerText = "bananone"
    dialog.show()

  val removeFishButton: BottomBarButton = BottomBarButton("/remove-fish.png")
  removeFishButton.onAction = (event: ActionEvent) => println("Clicked remove-fish")

  val playButton: BottomBarButton = BottomBarButton("/remove-fish.png")
  playButton.onAction = (event: ActionEvent) => println("Clicked play")

  val foodButton: BottomBarButton = BottomBarButton("/remove-fish.png")
  foodButton.onAction = (event: ActionEvent) => println("Clicked food")

  val cleanButton: BottomBarButton = BottomBarButton("/remove-fish.png")
  cleanButton.onAction = (event: ActionEvent) => println("Clicked clean")

  val bottomBar = new TilePane:
    background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
    children ++= Seq(
      addFishButton,
      removeFishButton,
      playButton,
      foodButton,
      cleanButton
    )

  bottomBar.alignment = Pos.Center
