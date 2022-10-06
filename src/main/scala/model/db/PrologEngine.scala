package model.db

import alice.tuprolog.{Prolog, SolveInfo, Struct, Term, Theory}
import model.fish.Fish
import model.Algae
import model.food.Food

/** Database methods to store and retrieve information. */
trait PrologEngine:

  /** Save all the information of passed fish. If the fish already exists informations are updated.
    *
    * @param fish
    *   The fish to be saved.
    */
  def saveFish(fish: Fish, iteration: Int): Unit

  /** Save all the information of passed algae. If the algae already exists informations are updated.
    *
    * @param algae
    *   The algae to be saved.
    */
  def saveAlgae(algae: Algae, iteration: Int): Unit

  /** Save all the information of passed food. If the food already exists informations are updated.
    *
    * @param food
    *   The food to be saved.
    */
  def saveFood(food: Food, iteration: Int): Unit

  /** Retrieve all saved fish in the simulation at the given iteration. Note: if you don't need all the fish from the
    * simulation, you should consider using [[PrologEngine.getAllHerbivorousFish]] or
    * [[PrologEngine.getAllCarnivorousFish]].
    *
    * @param iteration
    *   The number of the iteration to take the data from.
    *
    * @return
    *   An immutable list of fish.
    */
  def getAllFish(iteration: Int): List[Fish]

  /** Retrieve all saved herbivorous fish in the simulation at the given iteration. Note: if you want to retrieve all
    * the fish and filter them later, it is faster and better to use [[PrologEngine.getAllFish]].
    *
    * @param iteration
    *   The number of the iteration to take the data from.
    *
    * @return
    *   An immutable list of herbivorous fish.
    */
  def getAllHerbivorousFish(iteration: Int): List[Fish]

  /** Retrieve all saved carnivorous fish in the simulation at the given iteration. Note: if you want to retrieve all
    * the fish and filter them later, it is faster and better to use [[PrologEngine.getAllFish]].
    *
    * @param iteration
    *   The number of the iteration to take the data from.
    *
    * @return
    *   An immutable list of carnivorous fish.
    */
  def getAllCarnivorousFish(iteration: Int): List[Fish]

  /** Retrieve all saved algae in the simulation at the given iteration.
    *
    * @param iteration
    *   The number of the iteration to take the data from.
    *
    * @return
    *   An immutable list of algae.
    */
  def getAllAlgae(iteration: Int): List[Algae]

  /** Retrieve all saved food in the simulation at the given iteration. Note: if you don't need all the food from the
    * simulation, you should consider using [[PrologEngine.getAllHerbivorousFood]] or
    * [[PrologEngine.getAllCarnivorousFood]].
    *
    * @return
    *   An immutable list of food.
    */
  def getAllFood(iteration: Int): List[Food]

  /** Retrieve all saved herbivorous food in the simulation at the given iteration. Note: if you want to retrieve all
    * the food and filter them later, it is faster and better to use [[PrologEngine.getAllFood]].
    *
    * @return
    *   An immutable list of herbivorous food.
    */
  def getAllHerbivorousFood(iteration: Int): List[Food]

  /** Retrieve all saved carnivorous food in the simulation at the given iteration. Note: if you want to retrieve all
    * the food and filter them later, it is faster and better to use [[PrologEngine.getAllFood]].
    *
    * @return
    *   An immutable list of carnivorous food.
    */
  def getAllCarnivorousFood(iteration: Int): List[Food]

  /** Removes every data that has been added to the engine. */
  def clear: Unit

object PrologEngine extends PrologEngine:

  private val engine: Prolog = new Prolog()

  private def saveData(data: String): Unit =
    engine.addTheory(new Theory(data))

  private def getData[T](deserializer: Serializer[T])(query: Struct): List[T] =
    new Iterator[T] { // Bad brackets... but needed to prevent scalafmt to make code not compilable.
      var solution: SolveInfo = engine.solve(query)
      var go: Boolean = solution.isSuccess
      def hasNext: Boolean = go
      def next: T =
        val toRet: T = deserializer.deserialize(solution.getSolution.toString)
        if (solution.hasOpenAlternatives)
          solution = engine.solveNext()
        else
          go = false
        toRet
    }.toList

  override def saveFish(fish: Fish, iteration: Int = 0): Unit =
    saveData(FishSerializer.serialize(fish))

  override def saveAlgae(algae: Algae, iteration: Int = 0): Unit =
    saveData(AlgaeSerializer.serialize(algae))

  override def saveFood(food: Food, iteration: Int = 0): Unit =
    saveData(FoodSerializer.serialize(food))

  override def getAllFish(iteration: Int = 0): List[Fish] =
    val input: Struct = Struct("fish", Term.createTerm("N"), Term.createTerm("F"))
    getData(FishSerializer)(input)

  override def getAllHerbivorousFish(iteration: Int = 0): List[Fish] =
    val input: Struct = Struct("fish", Term.createTerm("N"), Term.createTerm("'H'"))
    getData(FishSerializer)(input)

  override def getAllCarnivorousFish(iteration: Int = 0): List[Fish] =
    val input: Struct = Struct("fish", Term.createTerm("N"), Term.createTerm("'C'"))
    getData(FishSerializer)(input)

  override def getAllAlgae(iteration: Int = 0): List[Algae] =
    val input: Struct = Struct("algae", Term.createTerm("B"), Term.createTerm("H"))
    getData(AlgaeSerializer)(input)

  override def getAllFood(iteration: Int = 0): List[Food] =
    val input: Struct =
      Struct("food", Term.createTerm("F"), Term.createTerm("A"), Term.createTerm("X"), Term.createTerm("Y"))
    getData(FoodSerializer)(input)

  override def getAllHerbivorousFood(iteration: Int = 0): List[Food] =
    val input: Struct =
      Struct("food", Term.createTerm("'H'"), Term.createTerm("A"), Term.createTerm("X"), Term.createTerm("Y"))
    getData(FoodSerializer)(input)

  override def getAllCarnivorousFood(iteration: Int = 0): List[Food] =
    val input: Struct =
      Struct("food", Term.createTerm("'C'"), Term.createTerm("A"), Term.createTerm("X"), Term.createTerm("Y"))
    getData(FoodSerializer)(input)

  override def clear: Unit =
    engine.clearTheory()
