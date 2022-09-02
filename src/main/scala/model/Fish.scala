package model

object Fish:
  var n: Int = 0
  val MAX_HUNGER: Int = 100

case class Fish():

  import model.Fish.{MAX_HUNGER, n}

  val name: String = "fish-" + n
  n += 1
  val hunger: Int = MAX_HUNGER
  val age: Int = 0
  val speed: (Double, Double) = (0.0, 0.0)

  def isDead: Boolean = hunger == 0
