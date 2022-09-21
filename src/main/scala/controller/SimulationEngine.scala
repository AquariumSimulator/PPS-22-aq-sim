package controller

import mvc.ControllerModule.Requirements
import model.aquarium.Aquarium

/** Control of simulation loop, speed, stop and resume. */
trait SimulationEngine:

  /** Called to make start or resume the simulation. */
  def start(simSpeed: SimulationSpeed): Unit

  /** Called to make stop the simulation. */
  def stop(): Unit

enum SimulationSpeed:
  case HALT, SLOW, NORMAL, FAST

object SimulationEngine:

  def apply(context: Requirements): SimulationEngine = new SimulationEngineImpl(context)

  private class SimulationEngineImpl(context: Requirements) extends SimulationEngine:

    import SimulationSpeed._

    val aquarium: Aquarium = context.model.initializeAquarium(10, 10, 10) // by user
    var speed: SimulationSpeed = HALT

    override def start(simSpeed: SimulationSpeed): Unit =
      speed match
        case HALT =>
          speed = simSpeed
        case _ => return
      var time = System.nanoTime()
      val thread: Thread = new Thread {
        override def run(): Unit =
          Iterator
            .iterate(aquarium)(context.model.step)
            .foreach((aq: Aquarium) =>
              speed match
                case HALT => return
                case _ =>

              context.view.renderSimulation(aq)

              val deltaTime = (System.nanoTime() - time) / 1_000_000

              Thread.sleep(speed match
                case SLOW => 1_000
                case NORMAL => 100
                case FAST => 10
                case _ =>
                  10_000
                    - deltaTime
              )

              time = System.nanoTime()
            )
      }
      thread.start

    override def stop(): Unit =
      speed = HALT
