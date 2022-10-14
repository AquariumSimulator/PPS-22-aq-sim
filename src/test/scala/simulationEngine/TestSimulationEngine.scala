package simulationEngine

import org.scalatest.funspec.AnyFunSpec

import controller.SimulationEngine

import org.scalatest.GivenWhenThen
import controller.SimulationSpeed
import model.aquarium._

import mvc.MVCTrait
import model.db.PrologEngine

/** Test for [[SimulationEngine]] */
class TestAlgae extends AnyFunSpec with GivenWhenThen:

  object FakeMVC extends MVCTrait:
    override val model = ModelImpl()
    override val view = FakeViewImpl()
    override val controller = ControllerImpl()

  describe("A new SimulationEngine") {
    it("should allow to be started") {

      Given("An aquarium")
      val aquarium: Aquarium = FakeMVC.model.initializeAquarium(0, 0, 0)

      Given("The simulation engine")
      val simEngine: SimulationEngine = SimulationEngine(aquarium)(using FakeMVC.given_ControllerRequirements)

      Then("Simulation is stopped")
      assert(!simEngine.isRunning())

      When("Simulation is started")
      simEngine.start(SimulationSpeed.FAST)

      Then("Simulation should be running")
      assert(simEngine.isRunning())

      When("Simulation is stopped")
      simEngine.stop()

      Then("Simulation should be not running")
      assert(!simEngine.isRunning())
    }
  }
