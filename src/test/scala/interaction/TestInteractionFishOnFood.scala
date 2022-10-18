package interaction

import org.scalatest.funspec.AnyFunSpec
import model.fish.*
import model.food.Food
import model.interaction.Interaction

/** Test for the [[InteractionFishOnFood]] */
class TestInteractionFishOnFood extends AnyFunSpec:
  
  private val fish = Fish(satiety = 50)
  private val food = Food(nutritionAmount = 10)
  private val interaction = Interaction(fish, food)

  describe("A fish") {
    it("should eat the food when he is hungry") {
      val updatedFishAndFood = interaction.update()
      assert(updatedFishAndFood._1.satiety == 60)
      assert(updatedFishAndFood._2.isEmpty)
    }

    it("shouldn't eat the food when he isn't hungry") {
      val newFish = Fish(satiety = 95)
      val newInteraction = Interaction(newFish, food)
      val updatedFishAndFood = newInteraction.update()
      assert(updatedFishAndFood._1.satiety == 95)
      assert(updatedFishAndFood._2.get.nutritionAmount == 10)
    }
  }