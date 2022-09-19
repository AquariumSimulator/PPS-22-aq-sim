package model

import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.fish.{FeedingType, Fish}
import mvc.MVC.model.*
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[ModelImpl]] */
class TestModel extends AnyFunSpec:
  private val herbivorousNumber = 5
  private val carnivorousNumber = 4
  private val algaeNumber = 3

  private val aquarium = initializeAquarium(herbivorousNumber, carnivorousNumber, algaeNumber)

  private def findAlgaePosition(set: Set[Algae]): Double =
    def _findAlgaePosition(base: Double): Double =
      base match
        case b if !set.contains(Algae(b)) => b
        case b => _findAlgaePosition(b + 1)

    _findAlgaePosition(0)

  describe("When Model.initializeAquarium is called") {
    describe("it should return a new Aquarium that") {
      it("should have a default AquariumState") {
        assert(aquarium.aquariumState == AquariumState())
      }
      it("should have a Population with the given amount of fish and algae") {
        assert(aquarium.population.herbivorous.size == herbivorousNumber)
        assert(aquarium.population.carnivorous.size == carnivorousNumber)
        assert(aquarium.population.algae.size == algaeNumber)
      }
      it("should have a default AvailableFood") {
        assert(aquarium.availableFood == AvailableFood())
      }
    }
  }
  describe("When Model.updateTemperatureByUser is called") {
    val newTemp = 15.5
    describe("it should return a new Aquarium that") {
      it(s"should have the temperature equal to $newTemp") {
        assert(updateTemperatureByUser(newTemp, aquarium).aquariumState.temperature == newTemp)
      }
    }
  }
  describe("When Model.updateBrightnessByUser is called") {
    val newBright = 65
    describe("it should return a new Aquarium that") {
      it(s"should have the brightness level equal to $newBright") {
        assert(updateBrightnessByUser(newBright, aquarium).aquariumState.brightness == newBright)
      }
    }
  }
  describe("When Model.cleanByUser is called") {
    describe("it should return a new Aquarium that") {
      it(s"should have the brightness level equal to 0") {
        assert(cleanByUser(aquarium).aquariumState.impurity == 0)
      }
    }
  }
  describe("When Model.updateOxygenationByUser is called") {
    val newOxygen = 5.5
    describe("it should return a new Aquarium that") {
      it(s"should have the brightness level equal to $newOxygen") {
        assert(updateOxygenationByUser(newOxygen, aquarium).aquariumState.oxygenation == newOxygen)
      }
    }
  }
  describe("When Model.addInhabitants is called") {
    describe("for adding an herbivorous fish") {
      it("should return an aquarium with a list of herbivorous fish that contains a new element") {
        assert(
          addInhabitant(
            aquarium,
            Fish(feedingType = FeedingType.HERBIVOROUS)
          ).population.herbivorous.size == aquarium.population.herbivorous.size + 1
        )
      }
    }
    describe("for adding an carnivorous fish") {
      it("should return an aquarium with a list of carnivorous fish that contains a new element") {
        assert(addInhabitant(aquarium, Fish()).population.carnivorous.size == aquarium.population.carnivorous.size + 1)
      }
    }
    describe("for adding an algae") {
      it("should return an aquarium with a list of algae that contains a new element") {
        assert(
          addInhabitant(
            aquarium,
            Algae(findAlgaePosition(aquarium.population.algae))
          ).population.algae.size == aquarium.population.algae.size + 1
        )
      }
    }
  }
  describe("When Model.removeInhabitants is called") {
    describe("for removing an herbivorous fish") {
      val firstElem = aquarium.population.herbivorous.head
      it("should return an aquarium with a list of herbivorous fish without the specified element") {
        val newAquarium = removeInhabitant(aquarium, firstElem).population.herbivorous
        assert(newAquarium.size == aquarium.population.herbivorous.size - 1)
        assert(!newAquarium.contains(firstElem))
      }
    }
    describe("for removing an carnivorous fish") {
      val firstElem = aquarium.population.carnivorous.head
      it("should return an aquarium with a list of carnivorous fish without the specified element") {
        val newAquarium = removeInhabitant(aquarium, firstElem).population.carnivorous
        assert(newAquarium.size == aquarium.population.carnivorous.size - 1)
        assert(!newAquarium.contains(firstElem))
      }
    }
    describe("for removing an algae") {
      val firstElem = aquarium.population.algae.head
      it("should return an aquarium with a list of algae without the specified element") {
        val newAquarium = removeInhabitant(aquarium, firstElem).population.algae
        assert(newAquarium.size == aquarium.population.algae.size - 1)
        assert(!newAquarium.contains(firstElem))
      }
    }
  }
  describe("When Model.addFood is called") {
    describe("for adding herbivorous food") {
      it("should return an aquarium with a list of herbivorous food containing a new element") {
        assert(
          addFood(
            aquarium,
            HerbivorousFood()
          ).availableFood.herbivorousFood.size == aquarium.availableFood.herbivorousFood.size + 1
        )
      }
    }
    describe("for adding carnivorous food") {
      it("should return an aquarium with a list of carnivorous food containing a new element") {
        assert(
          addFood(
            aquarium,
            CarnivorousFood()
          ).availableFood.carnivorousFood.size == aquarium.availableFood.carnivorousFood.size + 1
        )
      }
    }
  }
  describe("When Model.removeFood is called") {
    describe("for removing herbivorous food") {
      val newAquarium = addFood(aquarium, HerbivorousFood())
      it("should return an aquarium with a list of herbivorous food without an element") {
        assert(
          removeFood(
            aquarium,
            newAquarium.availableFood.herbivorousFood.head
          ).availableFood.herbivorousFood.isEmpty
        )
      }
    }
    describe("for removing carnivorous food") {
      val newAquarium = addFood(aquarium, CarnivorousFood())
      it("should return an aquarium with a list of carnivorous food without an element") {
        assert(
          removeFood(
            aquarium,
            newAquarium.availableFood.carnivorousFood.head
          ).availableFood.carnivorousFood.isEmpty
        )
      }
    }
  }
