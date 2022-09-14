package model.db

import alice.tuprolog.{Prolog, Theory}
import java.io.InputStream

object PrologEngine:
  val engine = new Prolog()
  engine.setTheory(new Theory(getClass.getResource("/mainTheory.pl").openStream()))
