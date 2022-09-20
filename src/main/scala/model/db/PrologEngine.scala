package model.db

import alice.tuprolog.{Prolog, SolveInfo, Struct, Term, Theory}
import model.fish.Fish
import model.Algae
import model.Food
import model.HerbivorousFood
import model.CarnivorousFood

import java.io.*
import java.util.Base64
import java.nio.charset.StandardCharsets.UTF_8
import scala.collection.mutable.ListBuffer

/** Database methods to store and retrieve information. */
trait PrologEngine:

  /** Save all the information of passed fish. If the fish already exists informations are updated.
    *
    * @param fish
    *   The fish to be saved.
    */
  def saveFish(fish: Fish): Unit

  /** Save a new relationship of parenting between two fishes.
    *
    * @param parent
    *   The parent fish.
    * @param son
    *   The son fish.
    */
  def saveSonOf(parent: Fish, son: Fish): Unit

  /** Save all the information of passed algae. If the algae already exists informations are updated.
    *
    * @param algae
    *   The algae to be saved.
    */
  def saveAlgae(algae: Algae): Unit

  /** Save all the information of passed herbivorous food. If the herbivorous food already exists informations are
    * updated.
    *
    * @param herbFood
    *   The herbivorous food to be saved.
    */
  def saveHerbFood(herbFood: HerbivorousFood): Unit

  /** Save all the information of passed carnivorous food. If the carnivorous food already exists informations are
    * updated.
    *
    * @param herbFood
    *   The carnivorous food to be saved.
    */
  def saveCarnFood(carnFood: CarnivorousFood): Unit

  /** Retrieve all saved fish in the simulation. Note: if you don't need all the fish from the simulation, you should
    * consider using [[PrologEngine.getAllHerbivorousFish]] or [[PrologEngine.getAllCarnivorousFish]].
    *
    * @return
    *   An immutable list of fish.
    */
  def getAllFish: List[Fish]

  /** Retrieve all saved herbivorous fish in the simulation. Note: if you want to retrieve all the fish and filter them
    * later, it is faster and better to use [[PrologEngine.getAllFish]].
    *
    * @return
    *   An immutable list of herbivorous fish.
    */
  def getAllHerbivorousFish: List[Fish]

  /** Retrieve all saved carnivorous fish in the simulation. Note: if you want to retrieve all the fish and filter them
    * later, it is faster and better to use [[PrologEngine.getAllFish]].
    *
    * @return
    *   An immutable list of carnivorous fish.
    */
  def getAllCarnivorousFish: List[Fish]

  /** Retrieve all saved algae in the simulation.
    *
    * @return
    *   An immutable list of algae.
    */
  def getAllAlgae: List[Algae]

  /** Retrieve all saved herbivorous food in the simulation.
    *
    * @return
    *   An immutable list of herbivorous food.
    */
  def getAllHerbivorousFood: List[HerbivorousFood]

  /** Retrieve all saved carnivorous food in the simulation.
    *
    * @return
    *   An immutable list of carnivorous food.
    */
  def getAllCarnivorousFood: List[CarnivorousFood]

//def getGenealogicalTreeOf(f: Fish): Unit // TODO

  /** Removes every data that has been added to the engine. */
  def clear: Unit

object PrologEngine extends PrologEngine:

  private val engine: Prolog = new Prolog()
  loadMainTheory()

  private def loadMainTheory(): Unit =
    engine.addTheory(new Theory(getClass.getResource("/prolog/mainTheory.pl").openStream()))

  private def saveData(data: String): Unit =
    engine.addTheory(new Theory(data))

  private def getFishData(query: Struct): List[Fish] =
    new Iterator[Fish] { // Bad brackets... but needed to prevent scalafmt to make code not compilable.
      var solution: SolveInfo = engine.solve(query)
      var go: Boolean = solution.isSuccess
      def hasNext: Boolean = go
      def next: Fish =
        val toRet: Fish = FishSerializer.deserialize(solution.getSolution.toString)
        if (solution.hasOpenAlternatives)
          solution = engine.solveNext()
        else
          go = false
        toRet
    }.to(List)

  private def getAlgaeData(query: Struct): List[Algae] =
    new Iterator[Algae] { // Bad brackets... but needed to prevent scalafmt to make code not compilable.
      var solution: SolveInfo = engine.solve(query)
      var go: Boolean = solution.isSuccess
      def hasNext: Boolean = go
      def next: Algae =
        val toRet: Algae = AlgaeSerializer.deserialize(solution.getSolution.toString)
        if (solution.hasOpenAlternatives)
          solution = engine.solveNext()
        else
          go = false
        toRet
    }.toList

  override def saveFish(fish: Fish): Unit =
    saveData(FishSerializer.serialize(fish))

  override def saveSonOf(parent: Fish, son: Fish): Unit = ???

  override def saveAlgae(algae: Algae): Unit =
    saveData(AlgaeSerializer.serialize(algae))

  override def saveHerbFood(herbFood: HerbivorousFood): Unit = ???

  override def saveCarnFood(carnFood: CarnivorousFood): Unit = ???

  override def getAllFish: List[Fish] =
    val input: Struct = Struct("fish", Term.createTerm("N"), Term.createTerm("F"))
    getFishData(input)

  override def getAllHerbivorousFish: List[Fish] =
    val input: Struct = Struct("fish", Term.createTerm("N"), Term.createTerm("'H'"))
    getFishData(input)

  override def getAllCarnivorousFish: List[Fish] =
    val input: Struct = Struct("fish", Term.createTerm("N"), Term.createTerm("'C'"))
    getFishData(input)

  override def getAllAlgae: List[Algae] =
    val input: Struct = Struct("algae", Term.createTerm("B"), Term.createTerm("H"))
    getAlgaeData(input)

  override def getAllCarnivorousFood: List[CarnivorousFood] = List.empty

  override def getAllHerbivorousFood: List[HerbivorousFood] = List.empty

  override def clear: Unit =
    engine.clearTheory()
    loadMainTheory()
