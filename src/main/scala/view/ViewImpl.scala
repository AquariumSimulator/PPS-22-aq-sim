package view

import mvc.ViewModule.Requirements
import view.View

/** View methods implementation from [[View]]. */
trait ViewImpl:
  context: Requirements =>
  class ViewImpl extends View:

    def show(i: Int): Unit = println(i)
    def update(): Unit = context.controller.notifyChange("changhed")
