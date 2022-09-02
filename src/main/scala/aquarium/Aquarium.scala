package aquarium

/** This class represent the current state of the simulation
  *
  * @param aquariumState
  *   represent the current state of the aquarium
  * @param population
  *   represent the current population of the aquarium
  */
case class Aquarium(aquariumState: AquariumState, population: Population, availableFood: AvailableFood)

/** Companion object of the case class */
object Aquarium:

  val aquariumState = AquariumState(
    InitializeAquarium.TEMPERATURE,
    InitializeAquarium.BRIGHTNESS,
    InitializeAquarium.PH,
    InitializeAquarium.IMPURITY,
    InitializeAquarium.OXYGENATION
  )
  /** Create a new [[Aquarium]]
    *
    * @param herbivorousFishesNumber
    *   number of herbivorous fishes
    * @param carnivorousFishesNumber
    *   number of carnivorous fishes
    * @param algaeNumber
    *   number of algae
    * @return
    *   an instance of the simulation (the initial one)
    */
  def apply(herbivorousFishesNumber: Int, carnivorousFishesNumber: Int, algaeNumber: Int): Aquarium =
    Aquarium(
      aquariumState,
      Population(herbivorousFishesNumber, carnivorousFishesNumber, algaeNumber),
      AvailableFood(Seq.empty, Seq.empty)
    )
