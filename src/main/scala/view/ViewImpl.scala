package view

import scalafx.stage.Stage
import scalafx.application.Platform

import mvc.ViewModule.ViewRequirements
import model.aquarium.Aquarium
import view.View
import view.widgets.SimulationViewer
import view.widgets.InfoPane
import view.widgets.slider.BrightnessSlider
import view.widgets.slider.TemperatureSlider
import view.widgets.slider.OxygenationSlider

/** View methods implementation from [[View]]. */
trait ViewImpl:
  context: ViewRequirements =>
  given ViewRequirements = context
  class ViewImpl extends View:
    def show(stage: Stage): Unit = GUI.start(stage)
    def renderSimulation(aquarium: Aquarium): Unit =
      SimulationViewer.renderSimulation(aquarium)
      Platform.runLater(() -> InfoPane.updateInfo(aquarium))
      Platform.runLater(
        () -> GUI.updateSliders(
          // aquarium.aquariumState.temperature,
          // aquarium.aquariumState.brightness,
          aquarium.aquariumState.oxygenation
        )
      )
