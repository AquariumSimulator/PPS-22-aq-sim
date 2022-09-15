package model.db

import alice.tuprolog.{Prolog, Theory}
import alice.tuprolog.SolveInfo
import model.fish.Fish
import model.Algae
import model.Food
import model.HerbivorousFood
import model.CarnivorousFood

object PrologEngine:
  val engine = new Prolog()
  engine.setTheory(new Theory(getClass.getResource("/prolog/mainTheory.pl").openStream()))

  engine.addTheory(new Theory("parent(banana, pippo)."))
  engine.addTheory(new Theory("parent(pippo, pera)."))
  engine.addTheory(new Theory("parent(bananino, pera)."))
  engine.addTheory(new Theory("parent(cipolla, bananino)."))

  println(engine.getTheory())

  println(engine.solve("parent(X, Y)."))
  while (engine.hasOpenAlternatives())
    println(engine.solveNext())

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
//def getGenealogicalTreeOf(f: Fish): Unit
