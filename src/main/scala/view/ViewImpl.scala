package view

import scalafx.stage.Stage

import mvc.ViewModule.Requirements
import model.aquarium.Aquarium
import view.View
import view.widgets.SimulationViewer

/** View methods implementation from [[View]]. */
trait ViewImpl:
  context: Requirements =>
  class ViewImpl extends View:
    def show(stage: Stage): Unit = GUI.start(stage, context)
    def renderSimulation(aquarium: Aquarium): Unit = SimulationViewer.renderSimulation(aquarium)
