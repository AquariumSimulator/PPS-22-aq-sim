package model

import model.FeedingType
import model.aquarium.{Aquarium, AquariumParametersLimits, AquariumState, AvailableFood, Population}
import model.fish.{Fish, UpdateFish}
import model.food.*
import model.interaction.Interaction
import mvc.MVC.model.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[ModelImpl]] */
class TestStep extends AnyFunSpec with BeforeAndAfterEach:

  private var aquariumState: AquariumState = _

  private val hunger: Int = 5

  private var hFishHungry: Fish = _
  private var hFishReproduction: Fish = _
  private var cFishHungry: Fish = _
  private var hFishNotHungry: Fish = _
  private var cFishNotHungry: Fish = _
  private var hFishEaten: Fish = _
  private var algaeEaten: Algae = _
  private var algaeNotEaten: Algae = _
  private var hFoodEaten: Food = _
  private var cFoodEaten: Food = _
  private var hFoodNotEaten: Food = _
  private var cFoodNotEaten: Food = _

  private var population: Population = _
  private var food: AvailableFood = _

  private var aquarium: Aquarium = _

  private var newAquarium: Aquarium = _
  private var entitySet: Set[Entity] = _

  override def beforeEach(): Unit =
    aquariumState = AquariumState()

    hFishHungry = Fish(
      position = (0, 0),
      feedingType = FeedingType.HERBIVOROUS,
      hunger = hunger,
      reproductionFactor = Fish.MAX_REPRODUCTION_FACTOR
    )
    hFishReproduction = Fish(
      position = (0, 0),
      feedingType = FeedingType.HERBIVOROUS,
      hunger = hunger,
      reproductionFactor = Fish.MAX_REPRODUCTION_FACTOR
    )
    cFishHungry = Fish(position = (1, 1), hunger = hunger)
    hFishNotHungry = Fish(position = (0, 1), feedingType = FeedingType.HERBIVOROUS)
    cFishNotHungry = Fish(position = (1, 0))
    hFishEaten = Fish(position = (1, 1), feedingType = FeedingType.HERBIVOROUS)
    algaeEaten = Algae()
    algaeNotEaten = Algae(2)
    hFoodEaten = Food(position = (0, 0), feedingType = FeedingType.HERBIVOROUS)
    cFoodEaten = Food(position = (1, 1))
    hFoodNotEaten = Food(position = (0, 1), feedingType = FeedingType.HERBIVOROUS)
    cFoodNotEaten = Food(position = (1, 0))

    population = Population(
      Set(hFishHungry, hFishNotHungry, hFishEaten, hFishReproduction),
      Set(cFishHungry, cFishNotHungry),
      Set(algaeEaten, algaeNotEaten)
    )
    food = AvailableFood(Set(hFoodEaten, hFoodNotEaten), Set(cFoodEaten, cFoodNotEaten))

    aquarium = Aquarium(aquariumState, population, food)

    newAquarium = step(aquarium)
    entitySet = population.herbivorous.concat(population.carnivorous).concat(population.algae)

  describe("When step() is called it return a new Aquarium where") {
    it("the new AquariumState is updated by all the inhabitant of the aquarium") {
      val aqState = aquariumState
        .copy(
          ph = aquariumState.ph + entitySet.map(e => e.phShift).sum,
          oxygenation = aquariumState.oxygenation + entitySet.map(e => e.oxygenShift).sum,
          impurity =
            aquariumState.impurity + population.carnivorous.concat(population.herbivorous).map(e => e.impurityShift).sum
        )
      assert(newAquarium.aquariumState.ph === aqState.ph +- 0.25)
      assert(newAquarium.aquariumState.impurity === aqState.impurity +- 0.1)
      assert(newAquarium.aquariumState.oxygenation === aqState.oxygenation +- 0.25)
    }

    it("the food near to the fish should be eaten") {
      assert(newAquarium.availableFood.herbivorousFood.size == food.herbivorousFood.size - 1)
      assert(newAquarium.availableFood.carnivorousFood.size == food.carnivorousFood.size - 1)
    }

    it("a fish that didn't eat anything has lower hunger") {
      // a fish that wasn't hungry and two new fish
      val fishNumber = 2 + 1
      assert(
        newAquarium.population.herbivorous.count(f => f.hunger == Fish.MAX_HUNGER - Fish.HUNGER_SHIFT) == fishNumber
      )
      assert(newAquarium.population.carnivorous.count(f => f.hunger == Fish.MAX_HUNGER - Fish.HUNGER_SHIFT) == 1)
    }

    it("an herbivorous fish that ate food and algae has an upper hunger") {
      newAquarium.population.herbivorous
        .filterNot(f => f.hunger == Fish.MAX_HUNGER - Fish.HUNGER_SHIFT)
        .foreach(f =>
          assert(
            f.hunger == hFishHungry.hunger + (algaeEaten.height * Algae.NUTRITION_AMOUNT) + hFoodEaten.nutritionAmount - Fish.HUNGER_SHIFT
          )
        )
    }

    it("an algae near to an hungry herbivorous fish is eaten") {
      assert(newAquarium.population.algae.size == aquarium.population.algae.size - 1)
    }

    it("an algae grows") {
      newAquarium.population.algae.foreach(a =>
        assert(a.height == Interaction(algaeNotEaten, newAquarium.aquariumState).update().get.height)
      )
    }

    it("an carnivorous fish that ate a fish has an upper hunger") {
      assert(!newAquarium.population.herbivorous.contains(hFishEaten))
    }

    it("two fish of the same kind, that are near, and have enough reproductive factor, reproduce") {
      newAquarium.population.herbivorous
        .filter(f => f.name == hFishHungry.name || f.name == hFishReproduction.name)
        .foreach(f =>
          assert(
            f.reproductionFactor == Fish.MAX_REPRODUCTION_FACTOR - (Fish.REPRODUCTION_COST * 2) + Fish.REPRODUCTION_FACTOR_SHIFT
          )
        )
      // two fish were born but one was eaten
      assert(newAquarium.population.herbivorous.size == aquarium.population.herbivorous.size + 2 - 1)
    }

  }
