package controller

import mvc.ControllerModule.Requirements
import model.aquarium.Aquarium

/** Control of simulation loop, speed, stop and resume. */
trait SimulationEngine:

  /** Called to make start the simulation. */
  def start(simSpeed: SimulationSpeed): Unit

  /** Called to make stop the simulation. */
  def stop(): Unit

enum SimulationSpeed:
  case HALT, SLOW, NORMAL, FAST

object SimulationEngine:

  def apply(context: Requirements): SimulationEngine = new SimulationEngineImpl(context)

  private class SimulationEngineImpl(context: Requirements) extends SimulationEngine:

    import SimulationSpeed._

    val aquarium: Aquarium = context.model.initializeAquarium(10, 10, 1) // by user
    var speed: SimulationSpeed = HALT

    override def start(simSpeed: SimulationSpeed): Unit =
      speed match
        case HALT =>
          speed = simSpeed
        case _ => return
      val thread: Thread = new Thread {
        override def run(): Unit =
          Iterator
            .iterate(aquarium)(context.model.step)
            .foreach((aq: Aquarium) =>
              speed match
                case HALT => Thread.currentThread().interrupt()
                case _ =>
              try
                Thread.sleep(speed match
                  case HALT | SLOW => 1000
                  case NORMAL => 100
                  case FAST => 10
                )
              catch
                case e: InterruptedException =>
                  println("Simulation correctly stopped.")
                  return ()
              context.view.renderSimulation(aq)
            )
      }
      thread.start

    override def stop(): Unit =
      speed = HALT
