package controller

import mvc.ControllerModule.ControllerRequirements
import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}

import scala.language.postfixOps

/** Control of simulation loop, speed, stop and resume. */
trait SimulationEngine:

  /** Called to make start or resume the simulation. If simSpeed is HALT only one step is taken. */
  def start(simSpeed: SimulationSpeed): Unit

  /** Called to pause the simulation. */
  def pause(): Unit

  /** Called to terminate the simulation. */
  def stop(): Unit

  /** Change the speed of simulation. */
  def changeSpeed(simSpeed: SimulationSpeed): Unit

  /** Get simulation speed. */
  def getSpeed(): SimulationSpeed

  /** True if simulation is running. */
  def isRunning(): Boolean

  /** Get current [[Aquarium]]. */
  def getAquarium(): Aquarium

  /** Get number of iterations. */
  def getIterations(): Int

enum SimulationSpeed:
  case HALT, SLOW, NORMAL, FAST, STOP

object SimulationEngine:

  def apply(aquarium: Aquarium)(using context: ControllerRequirements): SimulationEngine = SimulationEngineImpl(
    aquarium
  )

  private class SimulationEngineImpl(var aquarium: Aquarium)(using context: ControllerRequirements)
      extends SimulationEngine:

    import SimulationSpeed._

    var speed: SimulationSpeed = HALT
    var iteration: Int = 0

    override def getIterations(): Int = iteration

    val thread: Thread = new Thread {
      override def run(): Unit =
        var time = System.nanoTime()
        println("Simulation started")
        Iterator
          .iterate(aquarium)(context.model.step)
          .zipWithIndex
          .foreach((aq: Aquarium, index: Int) =>
            aquarium = aq
            iteration = index

            context.model.saveAquarium(aq, index)

            context.view.renderSimulation(aq)

            speed match
              case HALT =>
                println("Simulation stopped.")
                try Thread.sleep(Long.MaxValue)
                catch
                  case ex: InterruptedException =>
                    speed match
                      case STOP =>
                        println("Simulation finished.")
                        return
                      case HALT =>
                        println("Just one step.")
                      case _ => println("Simulation started.")
              case STOP =>
                println("Simulation finished.")
                return
              case _ =>

            val deltaTime = (System.nanoTime() - time) / 1_000_000

            try
              Thread.sleep(
                Math.max(
                  0,
                  (speed match
                    case SLOW => 500
                    case NORMAL => 150
                    case FAST => 50
                    case HALT | STOP => 0
                  )
                    - deltaTime
                )
              )
            catch case ex: InterruptedException => ()

            time = System.nanoTime()
          )
    }

    override def start(simSpeed: SimulationSpeed): Unit =
      speed match
        case HALT =>
          speed = simSpeed
          thread.isAlive() match
            case true => thread.interrupt
            case false => thread.start
        case _ =>

    override def pause(): Unit =
      speed = speed match
        case STOP => STOP
        case _ => HALT

    override def stop(): Unit =
      speed match
        case HALT => thread.interrupt
        case _ =>
      speed = STOP

    override def changeSpeed(simSpeed: SimulationSpeed): Unit =
      speed = simSpeed match
        case HALT | STOP => speed
        case _ => simSpeed

    override def getSpeed(): SimulationSpeed =
      speed

    override def isRunning(): Boolean =
      speed match
        case HALT | STOP => false
        case _ => true

    override def getAquarium(): Aquarium =
      aquarium
