package view.widgets

import model.{Algae, FeedingType}
import model.aquarium.{Aquarium, AquariumDimensions, Population}
import model.fish.Fish
import model.food.Food
import mvc.MVC.given_ViewRequirements as context
import mvc.ViewModule.ViewRequirements
import scalafx.Includes.*
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, ChoiceDialog, Tooltip}
import scalafx.scene.layout.{Background, BackgroundFill, TilePane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.stage.StageStyle
import view.utils.IconButton

import scala.util.Random
import controller.SimulationSpeed

object BottomBar:
  val addFishButton: BottomBarButton = BottomBarButton("/icons/add-fish.png")
  addFishButton.tooltip = new Tooltip("Add fish or algae")
  addFishButton.onAction = _ =>
    val dialog: ChoiceDialog[String] = new ChoiceDialog(
      defaultChoice = "Herbivorous Fish",
      choices = Seq("Herbivorous Fish", "Carnivorous Fish", "Algae")
    ):
      title = "Add fish or algae"
      headerText = "Choose the type"
    val choice: Option[String] = dialog.showAndWait()

    val newInhabitant = choice match
      case Some(str) if str == "Herbivorous Fish" =>
        Some(
          Fish(
            position = Population.randomPosition(),
            speed = Population.randomSpeed(),
            feedingType = FeedingType.HERBIVOROUS
          )
        )
      case Some(str) if str == "Carnivorous Fish" =>
        Some(Fish(position = Population.randomPosition(), speed = Population.randomSpeed()))
      case Some(_) => Some(Algae(base = Population.randomBase()))
      case None => None
    if newInhabitant.isDefined
    then context.controller.addInhabitant(newInhabitant.get)

  val changeSpeedButton: BottomBarButton = BottomBarButton("/icons/speed2.png")
  changeSpeedButton.disable = true
  changeSpeedButton.tooltip = Tooltip("Increase speed")
  changeSpeedButton.onAction = _ =>
    context.controller.getSpeed match
      case SimulationSpeed.SLOW =>
        context.controller.changeSpeed(SimulationSpeed.NORMAL)
        IconButton.setImage(
          changeSpeedButton,
          "/icons/speed2.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        changeSpeedButton.tooltip = Tooltip("Increase speed")
      case SimulationSpeed.NORMAL =>
        context.controller.changeSpeed(SimulationSpeed.FAST)
        IconButton.setImage(
          changeSpeedButton,
          "/icons/speed3.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        changeSpeedButton.tooltip = Tooltip("Increase speed")
      case SimulationSpeed.FAST =>
        context.controller.changeSpeed(SimulationSpeed.SLOW)
        IconButton.setImage(
          changeSpeedButton,
          "/icons/speed1.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        changeSpeedButton.tooltip = Tooltip("Increase speed")
      case _ =>

  val playButton: BottomBarButton = BottomBarButton("/icons/play.png")
  playButton.tooltip = Tooltip("Play the simulation")
  var speedBeforeStop: SimulationSpeed = SimulationSpeed.NORMAL
  playButton.onAction = _ =>
    context.controller.isRunning match
      case true =>
        speedBeforeStop = context.controller.getSpeed
        context.controller.pauseSimulation()
        IconButton.setImage(
          playButton,
          "/icons/play.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        playButton.tooltip = Tooltip("Play the simulation")
        changeSpeedButton.disable = true
      case false =>
        context.controller.startSimulation(speedBeforeStop)
        IconButton.setImage(
          playButton,
          "/icons/pause.png",
          BottomBarButton.DEFAULT_HEIGHT,
          BottomBarButton.DEFAULT_WIDTH
        )
        playButton.tooltip = Tooltip("Pause the simulation")
        changeSpeedButton.disable = false

  val foodButton: BottomBarButton = BottomBarButton("/icons/food.png")
  foodButton.tooltip = new Tooltip("Add food")
  foodButton.onAction = _ =>
    val dialog: ChoiceDialog[String] = new ChoiceDialog(
      defaultChoice = "Herbivorous Food",
      choices = Seq("Herbivorous Food", "Carnivorous Food")
    ):
      title = "Add food"
      headerText = "Choose the food type"
    val choice: Option[String] = dialog.showAndWait()
    val food = choice match
      case Some(str) if str == "Herbivorous Food" => Some(Food(feedingType = FeedingType.HERBIVOROUS))
      case Some(_) => Some(Food())
      case None => None
    if food.isDefined
    then context.controller.addFood(food.get)

  val cleanButton: BottomBarButton = BottomBarButton("/icons/clean.png")
  cleanButton.tooltip = Tooltip("Clean the aquarium")
  cleanButton.onAction = _ => context.controller.clean()

  val bottomBar: TilePane =
    new TilePane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      children ++= Seq(
        addFishButton, changeSpeedButton, playButton, foodButton, cleanButton
      )

  bottomBar.alignment = Pos.Center
