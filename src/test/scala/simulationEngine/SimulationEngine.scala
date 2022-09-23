package simulationEngine

import org.scalatest.funspec.AnyFunSpec

import controller.SimulationEngine

import mvc.MVC.model.initializeAquarium
import org.scalatest.GivenWhenThen
import controller.SimulationSpeed
import model.aquarium.Aquarium
import mvc.ControllerModule.ControllerRequirements

/** Test for [[SimulationEngine]] */
class TestAlgae extends AnyFunSpec with GivenWhenThen:
  describe("A new SimulationEngine") {
    it("should allow to be started") {

      Given("An aquarium")
      val aquarium: Aquarium = initializeAquarium(10, 10, 10)

      Given("The simulation engine")
      val simEngine: SimulationEngine = SimulationEngine(aquarium)(using )

      Then("Simulation is stopped")
      assert(!simEngine.isRunning())

      When("Simulation is started")
      simEngine.start(SimulationSpeed.FAST)

      Then("Simulation should be running")
      assert(simEngine.isRunning())

      When("Some time is passed")
      Thread.sleep(100)

      Then("Aquarium should be different from beginning")
      assert(aquarium !== simEngine.getAquarium())

      When("Simulation is stopped")
      simEngine.stop()

      Then("Simulation should be not running")
      assert(!simEngine.isRunning())
    }
  }
