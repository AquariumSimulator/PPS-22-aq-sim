package mvc

/** MVC implementation through cake pattern with separation of files for a better readability. */
object MVC extends ModelModule.Interface with ViewModule.Interface with ControllerModule.Interface:
  override val model = new ModelImpl()
  override val view = new ViewImpl()
  override val controller = new ControllerImpl()

  /*@main Entry point*/
  def main(args: Array[String]): Unit =
    view.show(model.m())

object ModelModule:

  import model.Model

  trait Provider:
    val model: Model

  import model.ModelImpl

  trait Interface extends Provider with ModelImpl

object ControllerModule:

  import controller.Controller

  trait Provider:
    val controller: Controller

  type Requirements = ViewModule.Provider with ModelModule.Provider

  import controller.ControllerImpl

  trait Interface extends Provider with ControllerImpl:
    self: Requirements =>

object ViewModule:

  import view.View

  trait Provider:
    val view: View

  type Requirements = ControllerModule.Provider

  import view.ViewImpl

  trait Interface extends Provider with ViewImpl:
    self: Requirements =>
