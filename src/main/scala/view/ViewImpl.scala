package view

import scalafx.stage.Stage

import mvc.ViewModule.ViewRequirements
import model.aquarium.Aquarium
import view.View
import view.widgets.SimulationViewer
import scalafx.application.Platform

/** View methods implementation from [[View]]. */
trait ViewImpl:
  context: ViewRequirements =>
  given ViewRequirements = context
  class ViewImpl extends View:
    def show(stage: Stage): Unit = GUI.start(stage)
    def renderSimulation(aquarium: Aquarium): Unit =
      Platform.runLater(
        SimulationViewer.renderSimulation(aquarium)
      )
