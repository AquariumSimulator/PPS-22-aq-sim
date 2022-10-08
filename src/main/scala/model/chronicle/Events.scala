package model.chronicle
import model.Algae
import model.Entity
import model.food.Food
import model.fish.Fish

object Messages:
  private def checkEntity[A](entity: A): String =
    entity match
      case f: Fish => f.name
      case _: Algae => "Algae"
      case _: Food => "Food"

  val CLEAN_AQUARIUM: String = "Aquarium was cleaned"

  val UPDATED_AQUARIUM_STATE_PARAMETER: (Double, String, String) => String =
    (value: Double, name: String, unitOfMeasure: String) =>
      val round = BigDecimal(value).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      name + " was updated: " + round + " " + unitOfMeasure

  def ADDED_ENTITY[A]: A => String =
    (entity: A) => checkEntity(entity) + " was added to the aquarium"

  def REMOVED_ENTITY[A]: A => String =
    (entity: A) => checkEntity(entity) + " was removed from the aquarium"

  val FISH_BIRTH: String => String =
    (name: String) => name + " was born"

  def ENTITY_DEATH[A]: A => String =
    (entity: A) => checkEntity(entity) + " was dead"

  val CHANGED_SPEED: String => String =
    (speed: String) => "Simulation speed updated: " + speed

  def FISH_ATE_ENTITY[A]: (String, A) => String =
    (nameFish: String, entity: A) => nameFish + " ate " + checkEntity(entity)
