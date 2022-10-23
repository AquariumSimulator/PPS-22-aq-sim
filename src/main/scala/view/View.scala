package view

import scalafx.stage.Stage
import model.aquarium.Aquarium

/** View trait implemented in [[ViewImpl]]. */
trait View:

  def show(stage: Stage): Unit

  def renderSimulation(aquarium: Aquarium): Unit
