package controller

import mvc.ControllerModule.Requirements
import model.aquarium.Aquarium

trait SimulationEngine:

  def start(): Unit

  def stop(): Unit

  def resume(): Unit

object SimulationEngine:

  def apply(context: Requirements): SimulationEngine = new SimulationEngineImpl(context)

  private class SimulationEngineImpl(context: Requirements) extends SimulationEngine:

    val aquarium: Aquarium = context.model.initializeAquarium(10, 10, 1) // by user

    override def start(): Unit =
      val thread: Thread = new Thread {
        override def run(): Unit =
          Iterator
            .iterate(aquarium)(context.model.step)
            .foreach((aq: Aquarium) =>
              Thread.sleep(1000)
              // println("Fish -> " + aq.population.herbivorous.head.position)
              println(aq.population.herbivorous.size.toString + " herbivorous fish")
              println(aq.population.carnivorous.size.toString + " carnivorous fish")
              println("-----")
              context.view.renderSimulation(aq)
            )
      }
      thread.start

    override def stop(): Unit = ???

    override def resume(): Unit = ???
