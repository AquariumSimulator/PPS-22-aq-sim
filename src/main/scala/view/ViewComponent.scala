package view

import scalafx.stage.Stage
import scalafx.application.Platform
import mvc.ViewModule.ViewRequirements
import model.aquarium.Aquarium
import view.View
import view.widgets.{Chronicle, InfoPane, SimulationViewer}
import view.widgets.slider.BrightnessSlider
import view.widgets.slider.TemperatureSlider
import view.widgets.slider.OxygenationSlider
import controller.SimulationSpeed

/** View methods implementation from [[View]]. */
trait ViewComponent:
  context: ViewRequirements =>
  given ViewRequirements = context

  class ViewImpl extends View:
    def show(stage: Stage): Unit =
      GUI.start(stage)
      context.controller.startSimulation(SimulationSpeed.HALT)
    def renderSimulation(aquarium: Aquarium): Unit =
      SimulationViewer.renderSimulation(aquarium)
      Platform.runLater(() -> InfoPane.updateInfo(aquarium))
      Platform.runLater(() -> Chronicle.update(context))
      Platform.runLater(
        () -> GUI.updateSliders(
          // aquarium.aquariumState.temperature,
          // aquarium.aquariumState.brightness,
          aquarium.aquariumState.oxygenation
        )
      )

  class FakeViewImpl extends View:
    override def show(stage: Stage): Unit = {}
    override def renderSimulation(aquarium: Aquarium): Unit = {}
