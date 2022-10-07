package chronicle
import model.Algae
import model.fish.Fish
import model.food.Food
import model.chronicle.Messages
import org.scalatest.funspec.AnyFunSpec
import controller.SimulationSpeed

class TestMessages extends AnyFunSpec:

  private val fish = Fish()
  private val fishEaten = Fish()
  private val algae = Algae()
  private val food = Food()

  private val cleanString = "Aquarium was cleaned"
  private val temp: Double = 12.3456789
  private val parameterString =
    "Temperature was updated: " + BigDecimal(temp).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble + " °C"
  private val addedFish = fish.name + " was added to the aquarium"
  private val addedAlgae = "Algae was added to the aquarium"
  private val addedFood = "Food was added to the aquarium"
  private val removedFish = fish.name + " was removed from the aquarium"
  private val removedAlgae = "Algae was removed from the aquarium"
  private val removedFood = "Food was removed from the aquarium"
  private val fishBirth = fish.name + " was born"
  private val deadFish = fish.name + " was dead"
  private val deadAlgae = "Algae was dead"
  private val deadFood = "Food was dead"
  private val changedSpeed = "Simulation speed updated: " + SimulationSpeed.NORMAL.toString
  private val fishAteFish = fish.name + " ate " + fishEaten.name
  private val fishAteAlgae = fish.name + " ate Algae"
  private val fishAteFood = fish.name + " ate Food"

  describe("When CLEAN_AQUARIUM is called") {
    it(s"should return a string equals to $cleanString") {
      assert(Messages.CLEAN_AQUARIUM == cleanString)
    }
  }

  describe("When UPDATED_AQUARIUM_STATE_PARAMETER is called") {
    it(s"should return a string equals to $parameterString") {
      assert(
        Messages.UPDATED_AQUARIUM_STATE_PARAMETER(temp, "Temperature", "°C") ==
          parameterString
      )
    }
  }

  describe("When ADDED_ENTITY is called on a fish") {
    it(s"should return a string equals to $addedFish") {
      assert(Messages.ADDED_ENTITY(fish) == addedFish)
    }
  }

  describe("When ADDED_ENTITY is called on an algae") {
    it(s"should return a string equals to $addedAlgae") {
      assert(Messages.ADDED_ENTITY(algae) == addedAlgae)
    }
  }

  describe("When ADDED_ENTITY is called on food") {
    it(s"should return a string equals to $addedFood") {
      assert(Messages.ADDED_ENTITY(food) == addedFood)
    }
  }

  describe("When REMOVED_ENTITY is called on a fish") {
    it(s"should return a string equals to $addedFish") {
      assert(Messages.REMOVED_ENTITY(fish) == removedFish)
    }
  }

  describe("When REMOVED_ENTITY is called on a algae") {
    it(s"should return a string equals to $addedAlgae") {
      assert(Messages.REMOVED_ENTITY(algae) == removedAlgae)
    }
  }

  describe("When REMOVED_ENTITY is called on a food") {
    it(s"should return a string equals to $addedFood") {
      assert(Messages.REMOVED_ENTITY(food) == removedFood)
    }
  }

  describe("When FISH_BIRTH is called") {
    it(s"should return a string equals to $fishBirth") {
      assert(Messages.FISH_BIRTH(fish.name) == fishBirth)
    }
  }

  describe("When ENTITY_DEATH is called on a fish") {
    it(s"should return a string equals to $deadFish") {
      assert(Messages.ENTITY_DEATH(fish) == deadFish)
    }
  }

  describe("When ENTITY_DEATH is called on an algae") {
    it(s"should return a string equals to $deadAlgae") {
      assert(Messages.ENTITY_DEATH(algae) == deadAlgae)
    }
  }

  describe("When ENTITY_DEATH is called on food") {
    it(s"should return a string equals to $deadFood") {
      assert(Messages.ENTITY_DEATH(food) == deadFood)
    }
  }

  describe("When CHANGED_SPEED is called") {
    it(s"should return a string equals to $changedSpeed") {
      Messages.CHANGED_SPEED(SimulationSpeed.NORMAL.toString) == changedSpeed
    }
  }

  describe("When FISH_ATE_ENTITY is called on a fish") {
    it(s"should return a string equals to $fishAteFish") {
      assert(Messages.FISH_ATE_ENTITY(fish.name, fishEaten) == fishAteFish)
    }
  }

  describe("When FISH_ATE_ENTITY is called on a algae") {
    it(s"should return a string equals to $fishAteAlgae") {
      assert(Messages.FISH_ATE_ENTITY(fish.name, algae) == fishAteAlgae)
    }
  }

  describe("When FISH_ATE_ENTITY is called on a food") {
    it(s"should return a string equals to $fishAteFood") {
      assert(Messages.FISH_ATE_ENTITY(fish.name, food) == fishAteFood)
    }
  }
