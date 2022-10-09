package model

import model.aquarium.{Aquarium, InitializeAquarium}
import model.chronicle.Events
import model.fish.Fish
import model.food.Food
import mvc.MVC.model
import org.scalatest.funspec.AnyFunSpec

/** Tests for [[model]] */
class TestModel extends AnyFunSpec:

  private val herbivorousNumber = 10
  private val carnivorousNumber = 20
  private val algaeNumber = 5
  private val aq = model.initializeAquarium(herbivorousNumber, carnivorousNumber, algaeNumber)
  private val aqEmpty = model.initializeAquarium(0, 0, 0)
  private val testEvent = "Test Event"

  describe("Given the interface Model") {
    describe("when addChronicleEvent is called") {
      model.addChronicleEvent(testEvent)
      it("chronicle should contain a new event") {
        assert(model.chronicle.events.nonEmpty)
      }
      it(s"and this event should be equal to $testEvent") {
        assert(model.chronicle.events.contains(testEvent))
      }
    }

    describe("When initializeAquarium is called") {
      describe("it should return a new aquarium where") {
        it(s"the number of the fish is equal to $carnivorousNumber plus $herbivorousNumber") {
          assert(aq.population.fish.size == herbivorousNumber + carnivorousNumber)
        }
        it(s"the number of herbivorous fish is equals to $herbivorousNumber") {
          assert(aq.population.herbivorous.size == herbivorousNumber)
        }
        it(s"the number of carnivorous fish is equals to $carnivorousNumber") {
          assert(aq.population.carnivorous.size == carnivorousNumber)
        }
        it(s"the number of algae is equals to $algaeNumber") {
          assert(aq.population.algae.size == algaeNumber)
        }
        it("the availableFood is empty") {
          assert(aq.availableFood.isEmpty)
        }
        it(s"the temperature is equals to ${InitializeAquarium.TEMPERATURE} ") {
          assert(aq.aquariumState.temperature == InitializeAquarium.TEMPERATURE)
        }
        it(s"the temperature is equals to ${InitializeAquarium.BRIGHTNESS} ") {
          assert(aq.aquariumState.brightness == InitializeAquarium.BRIGHTNESS)
        }
        it(s"the temperature is equals to ${InitializeAquarium.PH} ") {
          assert(aq.aquariumState.ph == InitializeAquarium.PH)
        }
        it(s"the temperature is equals to ${InitializeAquarium.IMPURITY} ") {
          assert(aq.aquariumState.impurity == InitializeAquarium.IMPURITY)
        }
        it(s"the temperature is equals to ${InitializeAquarium.OXYGENATION} ") {
          assert(aq.aquariumState.oxygenation == InitializeAquarium.OXYGENATION)
        }
      }
    }
  }

  describe("When addUserInteraction is called") {
    describe("to add food in the aquarium") {
      it("it should return an aquarium with an availableFood set containing a new element") {
        model.addUserInteraction((a: Aquarium) => a.addFood(Food()))
        val newAq = model.step(aq)
        assert(newAq.availableFood.size == 1)
        assert(newAq.herbivorousFood.isEmpty)
        assert(newAq.carnivorousFood.size == 1)
      }
    }

    describe("to add a fish in the aquarium") {
      it("it should return an aquarium with a fish set containing a new fish") {
        model.addUserInteraction((a: Aquarium) => a.copy(population = a.population.addInhabitant(Fish())))
        val newAq = model.step(aqEmpty)
        assert(newAq.population.fish.size == 1)
        assert(newAq.population.herbivorous.isEmpty)
        assert(newAq.population.carnivorous.size == 1)
      }
    }

    describe("to clean the aquarium") {
      it("it should return an aquarium with impurity set to 0") {
        model.addUserInteraction((a: Aquarium) => a.copy(aquariumState = a.aquariumState.updateImpurity(0)))
        val newAq = model.step(aq)
        assert(newAq.aquariumState.impurity == 0)
      }
    }

    describe("When saveAquarium is called on the first iteration") {
      model.saveAquarium(aq, 1)
      it("getDatabase should return a database containing the aquarium saved") {
        val db = model.getDatabase
        aq.population.fish.foreach(f => assert(db.getAllFish(1).contains(f)))
        aq.population.algae.foreach(a => assert(db.getAllAlgae(1).contains(a)))
        assert(db.getAllFood.isEmpty)
      }
    }
  }
