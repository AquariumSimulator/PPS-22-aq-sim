package model.chronicle
import model.fish.Fish
import model.food.Food
import model.{Algae, Entity}

/** Events to add to the chronicle */
object Events:

  /** Aquarium cleaning event */
  val CLEAN_AQUARIUM: String = "Aquarium was cleaned"

  /** Event for updating a parameter of the aquarium */
  val UPDATED_AQUARIUM_STATE_PARAMETER: (Double, String, String) => String =
    (value: Double, name: String, unitOfMeasure: String) =>
      val round = BigDecimal(value).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      name + " was updated: " + round + " " + unitOfMeasure

  /** Fish birth event */
  val FISH_BIRTH: String => String =
    (name: String) => name + " was born"

  /** Event for changing the speed of the simulation */
  val CHANGED_SPEED: String => String =
    (speed: String) => "Simulation speed updated: " + speed

  /** Event for adding an entity in the aquarium
    * @tparam A
    *   type of the entity
    * @return
    *   the event
    */
  def ADDED_ENTITY[A]: A => String =
    (entity: A) => checkEntity(entity) + " was added to the aquarium"

  /** Event for removing an entity in the aquarium
    * @tparam A
    *   type of the entity
    * @return
    *   the event
    */
  def REMOVED_ENTITY[A]: A => String =
    (entity: A) => checkEntity(entity) + " was removed from the aquarium"

  /** Death entity event
    * @tparam A
    *   type of the entity
    * @return
    *   the event
    */
  def ENTITY_DEATH[A]: A => String =
    (entity: A) => checkEntity(entity) + " was dead"

  /** Event of a fish eating an entity
    * @tparam A
    *   type of the entity eaten
    * @return
    *   the event
    */
  def FISH_ATE_ENTITY[A]: (String, A) => String =
    (nameFish: String, entity: A) => nameFish + " ate " + checkEntity(entity)

  /** Checks what type of entity it's the one in input and returns its name
    *
    * @param entity
    *   that has to be checked
    * @tparam A
    *   type of the entity
    * @return
    *   the name of the entity
    */
  private def checkEntity[A](entity: A): String =
    entity match
      case f: Fish => f.name
      case _: Algae => "Algae"
      case _: Food => "Food"
