package model.db

import alice.tuprolog.{Prolog, Theory}
import alice.tuprolog.SolveInfo
import model.fish.Fish
import model.Algae
import model.Food
import model.HerbivorousFood
import model.CarnivorousFood
import java.io._
import java.util.Base64
import java.nio.charset.StandardCharsets.UTF_8
import scala.collection.mutable.ListBuffer
import alice.tuprolog.Term

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

  /** Retrieve all saved fish in the simulation.
    *
    * @return
    *   An immutable list of fish.
    */
  def getAllFish: List[Fish]

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
  def getAllHerbFood: List[HerbivorousFood]

  /** Retrieve all saved carnivorous food in the simulation.
    *
    * @return
    *   An immutable list of carnivorous food.
    */
  def getAllCarnFood: List[CarnivorousFood]
//def getGenealogicalTreeOf(f: Fish): Unit

object PrologEngine extends PrologEngine:

  /*private*/ val engine: Prolog = new Prolog()
  engine.addTheory(new Theory(getClass.getResource("/prolog/mainTheory.pl").openStream()))

  private def saveData(data: String): Unit =
    engine.addTheory(new Theory(data))

  private def getData[A](query: String): List[A] =
    var results: ListBuffer[String] = new ListBuffer[String]()
    engine.solve(query)
    while (engine.hasOpenAlternatives)
      results += engine.solveNext().getSolution().toString
    engine.solveHalt()
    results.toList.map { s =>
      deserialize(s.replace("\'", "")).asInstanceOf[A]
    }

  private def serialize(value: Any): String =
    val stream: ByteArrayOutputStream = new ByteArrayOutputStream()
    val oos: ObjectOutputStream = new ObjectOutputStream(stream)
    oos.writeObject(value)
    oos.close()
    new String(
      Base64.getEncoder.encode(stream.toByteArray),
      UTF_8
    )

  private def deserialize(str: String): Any =
    val bytes = Base64.getDecoder.decode(str.getBytes(UTF_8))
    val ois = new ObjectInputStream(new ByteArrayInputStream(bytes))
    val value = ois.readObject
    ois.close()
    value

  override def saveFish(fish: Fish): Unit =
    saveData("fish(\"" + fish.name + "\", \"" + serialize(fish) + "\").")

  override def saveSonOf(parent: Fish, son: Fish): Unit = ???

  override def saveAlgae(algae: Algae): Unit = ???

  override def saveHerbFood(herbFood: HerbivorousFood): Unit = ???

  override def saveCarnFood(carnFood: CarnivorousFood): Unit = ???

  override def getAllFish: List[Fish] =
    getData[Fish]("fish(X, Y).")

  override def getAllAlgae: List[Algae] = List.empty

  override def getAllCarnFood: List[CarnivorousFood] = List.empty

  override def getAllHerbFood: List[HerbivorousFood] = List.empty
