package controller

import model.Algae
import model.aquarium.{Aquarium, AquariumState, InitializeAquarium, Population, AquariumDimensions}
import model.fish.Fish
import model.food.Food
import mvc.MVC.{controller, model}
import org.scalatest.funspec.AnyFunSpec

class TestController extends AnyFunSpec:

  private val temperature = 15.5
  private val brightness = 80
  private val oxygenation = 3

  private val food = Food(position = (AquariumDimensions.HEIGHT + 1, AquariumDimensions.WIDTH + 1))
  private val fish = Fish()

  private val testEvent = "Test event"

  private def update(aq: Aquarium): Aquarium = model.step(aq)

  describe("Given the interface Controller") {
    describe("when updateTemperature is called and a step is executed") {
      it(s"should return a new aquarium with the temperature equal to $temperature") {
        controller.updateTemperature(temperature)
        assert(update(controller.getAquarium).aquariumState.temperature == temperature)
      }
    }

    describe("when updateBrightness is called and a step is executed") {
      it(s"should return a new aquarium with the brightness equal to $brightness") {
        controller.updateBrightness(brightness)
        assert(update(controller.getAquarium).aquariumState.brightness == brightness)
      }
    }

    describe("when clean is called and a step is executed") {
      it(s"should return a new aquarium with the impurity equal to 0") {
        controller.clean()
        assert(update(controller.getAquarium).aquariumState.impurity == 0)
      }
    }

    describe("when updateOxygenation is called and a step is executed") {
      it(s"should return a new aquarium with the oxygenation equal to $oxygenation") {
        controller.updateOxygenation(oxygenation)
        assert(update(controller.getAquarium).aquariumState.oxygenation == oxygenation)
      }
    }

    describe(s"when addInhabitant is called to add $fish and a step is executed") {
      it(s"should return a new aquarium that contains $fish") {
        controller.addInhabitant(fish)
        val aq = update(controller.getAquarium)
        assert(aq.population.fish.contains(fish))
        assert(aq.population.carnivorous.contains(fish))
      }
    }

    describe(s"and when removeInhabitant is called to remove $fish and a step is executed") {
      it(s"should return a new aquarium that doesn't contain $fish") {
        controller.addInhabitant(fish)
        val aq = update(controller.getAquarium)
        controller.removeInhabitant(fish)
        val newAq = update(aq)
        assert(!newAq.population.fish.contains(fish))
        assert(!newAq.population.carnivorous.contains(fish))
      }
    }

//    describe(s"when addFood is called to add $food and a step is executed") {
//      it(s"should return a new aquarium that contains $food") {
//        controller.addFood(food)
//        val aq = update(controller.getAquarium)
//        assert(aq.availableFood.contains(food))
//        assert(aq.carnivorousFood.contains(food))
//      }
//    }

    describe(s"and when deleteFood is called to remove $food and a step is executed") {
      it(s"should return a new aquarium that doesn't contain $food") {
        controller.addFood(food)
        val aq = update(controller.getAquarium)
        controller.deleteFood(food)
        val newAq = update(aq)
        assert(!newAq.availableFood.contains(food))
        assert(!newAq.carnivorousFood.contains(food))
      }
    }

    describe("when getPopulationTrend is called") {
      it("should return the population of the aquarium at each iteration") {
        assert(controller.getPopulationTrend.head._1 == InitializeAquarium.HERBIVOROUS_FISH)
        assert(controller.getPopulationTrend.head._2 == InitializeAquarium.CARNIVOROUS_FISH)
        assert(controller.getPopulationTrend.head._3 == InitializeAquarium.ALGAE)
      }
    }

    describe(s"when getCurrentChronicle is called to add $testEvent") {
      it(s"should return a chronicle containing $testEvent") {
        model.addChronicleEvent(testEvent)
        assert(controller.getCurrentChronicle.events.nonEmpty)
        assert(controller.getCurrentChronicle.events.contains(testEvent))
      }
    }
  }
