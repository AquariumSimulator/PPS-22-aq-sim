package model

import model.FeedingType
import model.aquarium.{Aquarium, AquariumParametersLimits, AquariumState, AvailableFood, Population}
import model.fish.{Fish, UpdateFish}
import model.food.*
import model.interaction.Interaction
import mvc.MVC.model.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[ModelImpl]] */
class TestStep extends AnyFunSpec:

  private val aquariumState = AquariumState(ph = 0, oxygenation = 15)

  private val satiety = 5

  private val hFishHungry: Fish = Fish(
    position = (0, 0),
    feedingType = FeedingType.HERBIVOROUS,
    satiety = satiety,
    reproductionFactor = Fish.MAX_REPRODUCTION_FACTOR
  )
  private val hFishReproduction: Fish = Fish(
    position = (0, 0),
    feedingType = FeedingType.HERBIVOROUS,
    satiety = satiety,
    reproductionFactor = Fish.MAX_REPRODUCTION_FACTOR
  )
  private val cFishHungry: Fish = Fish(position = (11, 11), satiety = satiety)
  private val hFishEaten: Fish = Fish(position = (11, 11), feedingType = FeedingType.HERBIVOROUS)

  private val hFishNotHungry: Fish = Fish(position = (0, 100), feedingType = FeedingType.HERBIVOROUS)
  private val cFishNotHungry: Fish = Fish(position = (100, 0))

  private val algaeEaten: Algae = Algae()
  private val algaeNotEaten: Algae = Algae(base = 20)

  private val hFoodEaten: Food = Food(position = (0, 0), feedingType = FeedingType.HERBIVOROUS)
  private val cFoodEaten: Food = Food(position = (11, 11))
  private val hFoodNotEaten: Food = Food(position = (0, 100), feedingType = FeedingType.HERBIVOROUS)
  private val cFoodNotEaten: Food = Food(position = (100, 0))

  private val population =
    Population(
      Set(hFishHungry, hFishNotHungry, hFishEaten, hFishReproduction),
      Set(cFishHungry, cFishNotHungry),
      Set(algaeEaten, algaeNotEaten)
    )
  private val food =
    AvailableFood(Set(hFoodEaten, hFoodNotEaten), Set(cFoodEaten, cFoodNotEaten))

  private val aquarium = Aquarium(aquariumState, population, food)

  private val newAquarium = step(aquarium)
  private val entitySet = population.herbivorous.concat(population.carnivorous).concat(population.algae)

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

    it("a fish that didn't eat anything has lower satiety") {
      // a fish that wasn't hungry and a new fish
      val fishNumber = 1 + 1
      assert(
        newAquarium.population.herbivorous.count(f => f.satiety == Fish.MAX_SATIETY - Fish.SATIETY_SHIFT) == fishNumber
      )
      assert(newAquarium.population.carnivorous.count(f => f.satiety == Fish.MAX_SATIETY - Fish.SATIETY_SHIFT) == 1)
    }

    it("an herbivorous fish that ate food and algae has an upper satiety") {
      newAquarium.population.herbivorous
        .filterNot(f => f.satiety == Fish.MAX_SATIETY - Fish.SATIETY_SHIFT)
        .foreach(f =>
          assert(
            f.satiety == hFishHungry.satiety + (algaeEaten.height * Algae.NUTRITION_AMOUNT) + hFoodEaten.nutritionAmount - Fish.SATIETY_SHIFT
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

    it("an carnivorous fish that ate a fish has an upper satiety") {
      assert(!newAquarium.population.herbivorous.contains(hFishEaten))
    }

    it("two fish of the same kind, that are near, and have enough reproductive factor, reproduce") {
      newAquarium.population.herbivorous
        .filter(f => f.name == hFishHungry.name || f.name == hFishReproduction.name)
        .foreach(f =>
          assert(
            f.reproductionFactor == Fish.MAX_REPRODUCTION_FACTOR - Fish.REPRODUCTION_COST + Fish.REPRODUCTION_FACTOR_SHIFT
          )
        )
      // one fish was born but one was eaten
      assert(newAquarium.population.herbivorous.size == aquarium.population.herbivorous.size + 1 - 1)
    }

  }
