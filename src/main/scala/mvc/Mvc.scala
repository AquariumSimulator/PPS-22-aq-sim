package mvc

import scalafx.application.JFXApp3

trait MVCTrait extends ModelModule.Interface with ViewModule.Interface with ControllerModule.Interface

/** MVC implementation through cake pattern with separation of files for a better readability. */
object MVC extends MVCTrait with JFXApp3:
  override val model = ModelImpl()
  override val view = ViewImpl()
  override val controller = ControllerImpl()

  /*@main Entry point*/
  override def start(): Unit =
    view.show(new JFXApp3.PrimaryStage())

  /** Automatically stops the simulation when closing frame. */
  override def stopApp(): Unit =
    controller.stopSimulation()

object ModelModule:

  import model.Model

  trait Provider:
    val model: Model

  import model.ModelComponent

  trait Interface extends Provider with ModelComponent

object ControllerModule:

  import controller.Controller

  trait Provider:
    val controller: Controller

  type ControllerRequirements = ViewModule.Provider with ModelModule.Provider

  import controller.ControllerComponent

  trait Interface extends Provider with ControllerComponent:
    context: ControllerRequirements =>

object ViewModule:

  import view.View

  trait Provider:
    val view: View

  type ViewRequirements = ControllerModule.Provider

  import view.ViewComponent

  trait Interface extends Provider with ViewComponent:
    context: ViewRequirements =>
