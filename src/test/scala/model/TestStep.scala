package model

import model.FeedingType
import model.aquarium.*
import model.fish.{Fish, UpdateFish}
import model.food.*
import model.interaction.Interaction
import mvc.MVC.model.*
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funspec.AnyFunSpec

import scala.runtime.stdLibPatches.Predef.assert

/** Test for [[ModelImpl]] */
class TestStep extends AnyFunSpec:

  private val aquariumState = AquariumState()

  private val satiety = 5
  private val fishAge = 0

  private val herbivorousHungry: Fish = Fish(
    position = (0, AquariumDimensions.HEIGHT),
    feedingType = FeedingType.HERBIVOROUS,
    satiety = satiety,
    reproductionFactor = Fish.MAX_REPRODUCTION_FACTOR
  )

  private val herbivorousReproduction: Fish = Fish(
    position = (0, AquariumDimensions.HEIGHT),
    feedingType = FeedingType.HERBIVOROUS,
    satiety = satiety,
    reproductionFactor = Fish.MAX_REPRODUCTION_FACTOR
  )

  private val carnivorousHungry: Fish = Fish(position = (11, 11), satiety = satiety)

  private val herbivorousEaten: Fish = Fish(position = (11, 11), feedingType = FeedingType.HERBIVOROUS)

  private val herbivorousNotHungry: Fish = Fish(position = (0, 100), feedingType = FeedingType.HERBIVOROUS)
  private val carnivorousNotHungry: Fish = Fish(position = (100, 0))

  private val algaeEaten: Algae = Algae()
  private val algaeNotEaten: Algae = Algae(base = 20)

  private val hFoodEaten: Food =
    Food(position = (0, AquariumDimensions.HEIGHT), feedingType = FeedingType.HERBIVOROUS, nutritionAmount = 1)
  private val cFoodEaten: Food = Food(position = (11, 11), nutritionAmount = 2)
  private val hFoodNotEaten: Food =
    Food(position = (0, 100), feedingType = FeedingType.HERBIVOROUS, nutritionAmount = 3)
  private val cFoodNotEaten: Food = Food(position = (100, 0), nutritionAmount = 4)

  private val population =
    Population(
      Set(herbivorousHungry, herbivorousNotHungry, herbivorousEaten, herbivorousReproduction, carnivorousHungry,
        carnivorousNotHungry),
      Set(algaeEaten, algaeNotEaten)
    )
  private val food = Set(hFoodEaten, hFoodNotEaten, cFoodEaten, cFoodNotEaten)

  private val aquarium = Aquarium(aquariumState, population, food)

  private val newAquarium = step(aquarium)
  private val entities = population.fish.concat(population.algae).concat(food)

  private val newHerbivorousSatiety =
    herbivorousHungry.satiety + (algaeEaten.height * Algae.NUTRITION_AMOUNT) + hFoodEaten.nutritionAmount - Fish.SATIETY_SHIFT
  private val newCarnivorousSatiety =
    carnivorousHungry.satiety + (herbivorousEaten.size._1 * Fish.MEAT_AMOUNT) + cFoodEaten.nutritionAmount - Fish.SATIETY_SHIFT

  private val tolerance = 0.1

  population.herbivorous.foreach(f => println("--> " + f.name + " " + f.position))

  describe(s"When step is called it return a new ${Aquarium.getClass.getName} where") {
    it(s"the ${AquariumState.getClass.getName} is updated by all the inhabitant of the aquarium") {
      val state = aquariumState
        .copy(
          ph = aquariumState.ph + entities.map(e => e.phShift).sum,
          oxygenation = aquariumState.oxygenation + entities.map(e => e.oxygenShift).sum,
          impurity = aquariumState.impurity + entities.map(e => e.impurityShift).sum
        )
      assert(newAquarium.aquariumState.ph === state.ph +- tolerance)
      assert(newAquarium.aquariumState.impurity === state.impurity +- tolerance)
      assert(newAquarium.aquariumState.oxygenation === state.oxygenation +- tolerance)
    }

    it(s"all fish have age equals to the previous one plus ${Fish.AGE_SHIFT}") {
      newAquarium.population.fish
        .foreach(f => assert(f.age == fishAge + Fish.AGE_SHIFT))
    }

    it("the food near to the fish should be eaten") {
      assert(!newAquarium.availableFood.contains(hFoodEaten))
      assert(!newAquarium.availableFood.contains(cFoodEaten))
    }

    it("a fish that didn't eat anything has lower satiety") {
      // a fish that wasn't hungry and a new born fish
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
            f.satiety == newHerbivorousSatiety
          )
        )
    }

    it("an algae near to an hungry herbivorous fish is eaten") {
      assert(newAquarium.population.algae.size == aquarium.population.algae.size - 1)
    }

    it("an herbivorous fish near to an hungry carnivorous fish is eaten") {
      assert(!newAquarium.population.herbivorous.contains(herbivorousEaten))
    }

    it("an carnivorous fish that ate a fish has an upper satiety") {
      newAquarium.population.carnivorous
        .filterNot(f => f.satiety == Fish.MAX_SATIETY - Fish.SATIETY_SHIFT)
        .foreach(f =>
          assert(
            f.satiety == newCarnivorousSatiety.floor.toInt
          )
        )
    }

    it("algae grow") {
      newAquarium.population.algae.foreach(a =>
        assert(a.height == Interaction(algaeNotEaten, newAquarium.aquariumState).update().get.height)
      )
    }

    it("two fish of the same kind, that are near, and have enough reproductive factor, reproduce") {
      newAquarium.population.herbivorous
        .filter(f => f.name == herbivorousHungry.name || f.name == herbivorousReproduction.name)
        .foreach(f =>
          assert(
            f.reproductionFactor == Fish.MAX_REPRODUCTION_FACTOR - Fish.REPRODUCTION_COST + Fish.REPRODUCTION_FACTOR_SHIFT
          )
        )
      // one fish was born but one was eaten
      assert(newAquarium.population.herbivorous.size == aquarium.population.herbivorous.size + 1 - 1)
    }
  }
