package model

import model.aquarium.{Aquarium, InitializeAquarium}
import model.chronicle.Events
import model.fish.Fish
import model.food.Food
import mvc.MVC.model
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

/** Tests for [[model]] */
class TestModel extends AnyFunSpec:

  private val herbivorousNumber = 10
  private val carnivorousNumber = 20
  private val algaeNumber = 5
  private val aquarium = model.initializeAquarium(herbivorousNumber, carnivorousNumber, algaeNumber)
  private val emptyAquarium = model.initializeAquarium(0, 0, 0)
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
      describe(s"it should return a new ${Aquarium.getClass.getName} where") {
        it(s"the number of the fish is equal to $carnivorousNumber + $herbivorousNumber") {
          assert(aquarium.population.fish.size == herbivorousNumber + carnivorousNumber)
        }
        it(s"the number of herbivorous fish is equals to $herbivorousNumber") {
          assert(aquarium.population.herbivorous.size == herbivorousNumber)
        }
        it(s"the number of carnivorous fish is equals to $carnivorousNumber") {
          assert(aquarium.population.carnivorous.size == carnivorousNumber)
        }
        it(s"the number of algae is equals to $algaeNumber") {
          assert(aquarium.population.algae.size == algaeNumber)
        }
        it("the available food set is empty") {
          assert(aquarium.availableFood.isEmpty)
        }
        it(s"the temperature is equals to ${InitializeAquarium.TEMPERATURE} ") {
          assert(aquarium.aquariumState.temperature == InitializeAquarium.TEMPERATURE)
        }
        it(s"the brightness is equals to ${InitializeAquarium.BRIGHTNESS} ") {
          assert(aquarium.aquariumState.brightness == InitializeAquarium.BRIGHTNESS)
        }
        it(s"the PH is equals to ${InitializeAquarium.PH} ") {
          assert(aquarium.aquariumState.ph == InitializeAquarium.PH)
        }
        it(s"the impurity is equals to ${InitializeAquarium.IMPURITY} ") {
          assert(aquarium.aquariumState.impurity == InitializeAquarium.IMPURITY)
        }
        it(s"the oxygenation is equals to ${InitializeAquarium.OXYGENATION} ") {
          assert(aquarium.aquariumState.oxygenation == InitializeAquarium.OXYGENATION)
        }
      }
    }
  }

  describe("When addUserInteraction is called") {
    describe("to add food in the aquarium") {
      it(s"it should return an ${Aquarium.getClass.getName} with an available food set containing a new element") {
        model.addUserInteraction((a: Aquarium) => a.addFood(Food()))
        val newAq = model.step(aquarium)
        assert(newAq.availableFood.size == 1)
        assert(newAq.herbivorousFood.isEmpty)
        assert(newAq.carnivorousFood.size == 1)
      }
    }

    describe("to add a fish in the aquarium") {
      it(s"it should return an ${Aquarium.getClass.getName} with a fish set containing a new fish") {
        model.addUserInteraction((a: Aquarium) => a.copy(population = a.population.addInhabitant(Fish())))
        val newAq = model.step(emptyAquarium)
        assert(newAq.population.fish.size == 1)
        assert(newAq.population.herbivorous.isEmpty)
        assert(newAq.population.carnivorous.size == 1)
      }
    }

    describe("to clean the aquarium") {
      it(
        s"it should return an ${Aquarium.getClass.getName} with impurity equals to the new level of impurity of the step"
      ) {
        model.addUserInteraction((a: Aquarium) => a.copy(aquariumState = a.aquariumState.updateImpurity(0)))
        val newAq = model.step(aquarium)
        assert(
          newAq.aquariumState.impurity === aquarium.population.fish
            .concat(aquarium.population.algae)
            .concat(aquarium.availableFood)
            .map(e => e.impurityShift)
            .sum +- 0.1
        )
      }
    }

    describe("When saveAquarium is called on the first iteration") {
      model.saveAquarium(aquarium, 1)
      it("getDatabase should return a database containing the aquarium saved") {
        val db = model.getDatabase
        aquarium.population.fish.foreach(f => assert(db.getAllFish(1).contains(f)))
        aquarium.population.algae.foreach(a => assert(db.getAllAlgae(1).contains(a)))
        assert(db.getAllFood.isEmpty)
      }
    }
  }
