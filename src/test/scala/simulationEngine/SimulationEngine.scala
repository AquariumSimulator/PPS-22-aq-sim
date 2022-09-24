// package simulationEngine

// import org.scalatest.funspec.AnyFunSpec

// import controller.SimulationEngine

// import mvc.MVC.model.initializeAquarium
// import org.scalatest.GivenWhenThen
// import controller.SimulationSpeed
// import model.aquarium.Aquarium
// import mvc.ControllerModule.ControllerRequirements
// import mvc.ViewModule.{Provider => ViewProvider}

// import scalafx.stage.Stage
// import view.View
// import mvc.MVC

// /** Test for [[SimulationEngine]] */
// class TestAlgae extends AnyFunSpec with GivenWhenThen:

//   trait MockProvider:
//     val view = new View:
//       override def show(stage: Stage): Unit = {}
//       override def renderSimulation(aquarium: Aquarium): Unit = {}

//   type MockRequirements = ControllerRequirements with MockProvider

//   describe("A new SimulationEngine") {
//     it("should allow to be started") {

//       Given("An aquarium")
//       val aquarium: Aquarium = initializeAquarium(10, 10, 10)

//       Given("The simulation engine")
//       val simEngine: SimulationEngine = SimulationEngine(aquarium)(using context)

//       Then("Simulation is stopped")
//       assert(!simEngine.isRunning())

//       When("Simulation is started")
//       simEngine.start(SimulationSpeed.FAST)

//       Then("Simulation should be running")
//       assert(simEngine.isRunning())

//       When("Some time is passed")
//       Thread.sleep(100)

//       Then("Aquarium should be different from beginning")
//       assert(aquarium !== simEngine.getAquarium())

//       When("Simulation is stopped")
//       simEngine.stop()

//       Then("Simulation should be not running")
//       assert(!simEngine.isRunning())
//     }
//   }
