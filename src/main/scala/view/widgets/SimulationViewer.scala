package view.widgets

import javafx.scene.input.MouseEvent
import model.aquarium.{Aquarium, AquariumDimensions}
import model.fish.Fish
import model.food.Food
import model.{Algae, Entity, FeedingType}
import mvc.MVC.given_ViewRequirements as context
import scalafx.geometry.Insets
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.{Background, BackgroundFill, BorderPane}
import scalafx.scene.paint.{Color, LinearGradient, Stops}

import scala.language.postfixOps
import scala.util.Random

object SimulationViewer:

  private val preferredWidth: Int = 500
  private val preferredHeight: Int = 500

  private val canvas: Canvas = new Canvas:
    managed = true
    width = preferredWidth
    height = preferredHeight

  val canvasPane = new BorderPane:
    center = canvas
    background = new Background(
      Array(
        new BackgroundFill(
          new LinearGradient(
            endX = 0,
            stops = Stops(
              Color.rgb(0, 191, 255),
              Color.rgb(25, 25, 112)
            )
          ),
          // Color.rgb(171, 205, 239),
          null,
          null
        )
      )
    )

  canvasPane.setOnMouseClicked((mouseEvent: MouseEvent) =>
    findEntityClicked(mouseEvent.getX, mouseEvent.getY) match
      case Some(e: Entity) =>
        e match
          case e: Food =>
            context.controller.deleteFood(e.copy(position = (e.position._1, e.position._2 + Food.SPEED._2)))
          case _ => context.controller.removeInhabitant(e)
      case None => ()
  )

  private val gc = canvas.graphicsContext2D

  private val greenFish: Image = new Image("/img/green-fish.png")
  private val redFish: Image = new Image("/img/red-fish.png")
  private val algaeImage: Image = new Image("/img/seaweed.png")
  private val meat: Image = new Image("/img/meat.png")
  private val herbFood: Image = new Image("/img/lettuce.png")

  renderSimulation(context.controller.getAquarium())

  def findEntityClicked(coordinates: (Double, Double)): Option[Entity] =
    val aquarium = context.controller.getAquarium()
    val entities: Set[Entity] =
      aquarium.population.algae.concat(aquarium.population.fish).concat(aquarium.availableFood)
    val entitiesClicked: Set[Entity] = entities.filter(e =>
      val topLeft: (Double, Double) = mapToCanvasCoordinate(e.position)
      val bottomRight: (Double, Double) = mapToCanvasCoordinate(e.position._1 + e.size._1, e.position._2 + e.size._2)
      (coordinates._1 > topLeft._1 && coordinates._1 < bottomRight._1) &&
      (coordinates._2 > topLeft._2 && coordinates._2 < bottomRight._2) ||
      (coordinates._2 > (preferredHeight - bottomRight._2)) &&
      (coordinates._1 < (topLeft._1 + bottomRight._2 / 2)) && (coordinates._1 > topLeft._1)
    )
    entitiesClicked.headOption

  def renderSimulation(aquarium: Aquarium): Unit =
    gc.clearRect(0, 0, canvas.width.value, canvas.height.value)
    aquarium.population.herbivorous.foreach((fish: Fish) => drawFish(fish))
    aquarium.population.carnivorous.foreach((fish: Fish) => drawFish(fish))
    aquarium.population.algae.foreach((a: Algae) => drawAlgae(a))
    aquarium.carnivorousFood.foreach((f: Food) => drawFood(meat, f.position))
    aquarium.herbivorousFood.foreach((f: Food) => drawFood(herbFood, f.position))

  private def drawFood(foodImage: Image, coordinate: (Double, Double)): Unit =
    val canvasCoordinate: (Double, Double) = mapToCanvasCoordinate(coordinate)
    gc.drawImage(foodImage, canvasCoordinate._1, canvasCoordinate._2, 50, 50)

  private def drawAlgae(algae: Algae): Unit =
    val canvasCoordinate: (Double, Double) = mapToCanvasCoordinate(algae.position)
    gc.drawImage(
      algaeImage,
      canvasCoordinate._1,
      preferredHeight - canvasCoordinate._2 - algae.height,
      30,
      algae.height
    )

  private def drawFish(fish: Fish): Unit =
    var positionOnCanvas: (Double, Double) = mapToCanvasCoordinate(fish.position)
    var sizeOnCanvas: (Double, Double) = mapToCanvasCoordinate(fish.size)
    val image: Image = if (fish.feedingType == FeedingType.HERBIVOROUS) greenFish else redFish
    if fish.speed._1 < 0 then
      positionOnCanvas = (positionOnCanvas._1 + sizeOnCanvas._1, positionOnCanvas._2)
      sizeOnCanvas = (-sizeOnCanvas._1, sizeOnCanvas._2)
    gc.drawImage(
      image,
      positionOnCanvas._1,
      positionOnCanvas._2,
      sizeOnCanvas._1,
      sizeOnCanvas._2
    )

  private def mapToCanvasCoordinate(position: (Double, Double)): (Double, Double) =
    (
      position._1 / AquariumDimensions.WIDTH * preferredWidth,
      position._2 / AquariumDimensions.HEIGHT * preferredHeight
    )
