package model.db

import alice.tuprolog.{Prolog, Theory}
import alice.tuprolog.SolveInfo
import model.fish.Fish

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

trait PrologEngine:
  def saveFish(f: Fish): Unit
  def saveSonOf(parent: Fish, son: Fish): Unit
//def getGenealogicalTreeOf(f: Fish): Unit
