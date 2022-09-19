package view

import scalafx.stage.Stage

import mvc.ViewModule.Requirements
import view.View

/** View methods implementation from [[View]]. */
trait ViewImpl:
  context: Requirements =>
  class ViewImpl extends View:
    def show(stage: Stage): Unit = GUI.start(stage)
    def update(): Unit = context.controller.notifyChange("changhed")
