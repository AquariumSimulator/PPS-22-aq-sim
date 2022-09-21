package view.widgets

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, ChoiceDialog, Tooltip}
import scalafx.scene.layout.{Background, BackgroundFill, TilePane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.stage.StageStyle
import view.utils.IconButton

object BottomBar:
  var uselessBoolean: Boolean = true // TODO: remove when no more needed

  val addFishButton: BottomBarButton = BottomBarButton("/icons/add-fish.png")
  addFishButton.tooltip = new Tooltip("Add fish or algae")
  addFishButton.onAction = (event: ActionEvent) =>
    val dialog: ChoiceDialog[String] = new ChoiceDialog(
      defaultChoice = "Herbivorous Fish",
      choices = Seq("Herbivorous Fish", "Carnivorous Fish", "Algae")
    ):
      title = "Add fish or algae"
      headerText = "Choose the type"
    // initStyle(StageStyle.Utility)
    val choice: Option[String] = dialog.showAndWait()
    choice match
      case Some(str) => println("Chosen " + str)
      case None => println("Chosen nothing")

  val removeFishButton: BottomBarButton = BottomBarButton("/icons/remove-fish.png")
  removeFishButton.tooltip = Tooltip("Remove fish")
  removeFishButton.onAction = (event: ActionEvent) => println("Clicked remove-fish")

  val playButton: BottomBarButton = BottomBarButton("/icons/play.png")
  playButton.tooltip = Tooltip("Play the simulation")
  playButton.onAction = (event: ActionEvent) =>
    uselessBoolean match
      case true =>
        println("Clicked play")
        IconButton.setImage(
          playButton,
          "/icons/pause.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        playButton.tooltip = Tooltip("Pause the simulation")
      case false =>
        println("Clicked pause")
        IconButton.setImage(
          playButton,
          "/icons/play.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        playButton.tooltip = Tooltip("Play the simulation")
    uselessBoolean = !uselessBoolean

  val foodButton: BottomBarButton = BottomBarButton("/icons/food.png")
  foodButton.tooltip = new Tooltip("Add food")
  foodButton.onAction = (event: ActionEvent) =>
    val dialog: ChoiceDialog[String] = new ChoiceDialog(
      defaultChoice = "Herbivorous Food",
      choices = Seq("Herbivorous Food", "Carnivorous Food")
    ):
      title = "Add food"
      headerText = "Choose the food type"
    // initStyle(StageStyle.Utility)
    val choice: Option[String] = dialog.showAndWait()
    choice match
      case Some(str) => println("Chosen " + str)
      case None => println("Chosen nothing")

  val cleanButton: BottomBarButton = BottomBarButton("/icons/clean.png")
  cleanButton.tooltip = Tooltip("Clean the aquarium")
  cleanButton.onAction = (event: ActionEvent) => println("Clicked clean")

  val bottomBar = new TilePane:
    background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
    children ++= Seq(
      addFishButton, removeFishButton, playButton, foodButton, cleanButton
    )

  bottomBar.alignment = Pos.Center
