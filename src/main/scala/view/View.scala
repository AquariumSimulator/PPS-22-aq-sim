package view

import scalafx.stage.Stage

/** View trait implemented in [[ViewImpl]]. */
trait View:

  def show(stage: Stage): Unit
