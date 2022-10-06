package model.chronicle

object Messages:

  val CLEAN_AQUARIUM: String = "Aquarium was cleaned"

  val UPDATED_AQUARIUM_STATE_PARAMETER: (Double, String, String) => String =
    (value: Double, name: String, unitOfMeasure: String) => name + " was updated: " + value + unitOfMeasure

  val ADDED_ENTITY: String => String =
    (entity: String) => entity + " was added to the aquarium"

  val REMOVED_ENTITY: String => String =
    (entity: String) => entity + " was removed from the aquarium"

  val FISH_BIRTH: String => String =
    (name: String) => name + " was born"

  val ENTITY_DEATH: String => String =
    (entity: String) => entity + "was dead"

  val CHANGED_SPEED: String => String =
    (speed: String) => "Simulation speed updated: " + speed

  val FISH_ATE_ENTITY: (String, String) => String =
    (nameFish: String, entity: String) => nameFish + " ate " + entity
