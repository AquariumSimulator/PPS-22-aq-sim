package controller

import mvc.ControllerModule.ControllerRequirements
import model.aquarium.Aquarium
//import mvc.MVC.{given_ControllerRequirements => context}

/** Control of simulation loop, speed, stop and resume. */
trait SimulationEngine:

  /** Called to make start or resume the simulation. */
  def start(simSpeed: SimulationSpeed): Unit

  /** Called to make stop the simulation. */
  def stop(): Unit

  /** Change the speed of simulation. */
  def changeSpeed(simSpeed: SimulationSpeed): Unit

  /** True if simulation is running. */
  def isRunning(): Boolean

  /** Get current [[Aquarium]]. */
  def getAquarium(): Aquarium

enum SimulationSpeed:
  case HALT, SLOW, NORMAL, FAST

object SimulationEngine:

  def apply(aquarium: Aquarium)(using context: ControllerRequirements): SimulationEngine = new SimulationEngineImpl(
    aquarium
  )

  private class SimulationEngineImpl(var aquarium: Aquarium)(using context: ControllerRequirements)
      extends SimulationEngine:

    import SimulationSpeed._

    var speed: SimulationSpeed = HALT

    override def start(simSpeed: SimulationSpeed): Unit =
      speed match
        case HALT =>
          speed = simSpeed
        case _ => return
      var time = System.nanoTime()
      val thread: Thread = new Thread {
        override def run(): Unit =
          println("Simulation started")
          Iterator
            .iterate(aquarium)(context.model.step)
            .foreach((aq: Aquarium) =>
              aquarium = aq

              speed match
                case HALT =>
                  println("Simulation stopped")
                  return
                case _ =>

              context.view.renderSimulation(aq)

              val deltaTime = (System.nanoTime() - time) / 1_000_000

              Thread.sleep((speed match
                case SLOW => 1_000
                case NORMAL => 100
                case FAST => 10
                case _ => 10_000
              )
                - deltaTime)

              time = System.nanoTime()
            )
      }
      thread.start

    override def stop(): Unit =
      speed = HALT

    override def changeSpeed(simSpeed: SimulationSpeed): Unit =
      speed = simSpeed match
        case HALT => speed
        case _ => simSpeed

    override def isRunning(): Boolean =
      speed match
        case HALT => false
        case _ => true

    override def getAquarium(): Aquarium =
      aquarium
