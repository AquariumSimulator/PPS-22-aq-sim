package simulationEngine

import org.scalatest.funspec.AnyFunSpec

import controller.SimulationEngine

import org.scalatest.GivenWhenThen
import controller.SimulationSpeed
import model.aquarium._

import scalafx.stage.Stage
import view.View
import mvc.MVCTrait
import controller.Controller
import model.Model
import model.ModelImpl
import model.db.PrologEngine

/** Test for [[SimulationEngine]] */
class TestAlgae extends AnyFunSpec with GivenWhenThen:

  object FakeMVC extends MVCTrait:
    override val controller: Controller = ControllerImpl()
    override val model: Model = new Model:

      override def saveAquarium(aquarium: Aquarium, iteration: Int): Unit = ???

      override def getDatabase(): PrologEngine = ???

      override def initializeAquarium(
          herbivorousFishNumber: Int,
          carnivorousFishNumber: Int,
          algaeNumber: Int
      ): Aquarium =
        Aquarium(AquariumState(), Population(Set.empty, Set.empty), Set.empty)

      override def step(aquarium: Aquarium): Aquarium = ???

      override def addUserInteraction(interaction: Aquarium => Aquarium): Unit = ???
    override val view = new View:
      override def show(stage: Stage): Unit = {}
      override def renderSimulation(aquarium: Aquarium): Unit = {}

  import FakeMVC.given_ControllerRequirements

  describe("A new SimulationEngine") {
    it("should allow to be started") {

      Given("An aquarium")
      val aquarium: Aquarium = FakeMVC.model.initializeAquarium(0, 0, 0)

      Given("The simulation engine")
      val simEngine: SimulationEngine = SimulationEngine(aquarium)

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
