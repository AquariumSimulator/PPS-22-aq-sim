import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random

import model.food.{Food, HerbivorousFood, CarnivorousFood}

class TestTwoFood extends AnyFunSuite:
  val fishTypes: List[() => Food] = List(
    () => HerbivorousFood((Random.between(0.0, 10.0), Random.between(0.0, 10.0))),
    () => CarnivorousFood((Random.between(0.0, 10.0), Random.between(0.0, 10.0)))
  )

  fishTypes.foreach { i =>
    val f = i()
    fishTypes.foreach { j =>
      val f2 = j()
      test(s"A ${f.getClass.getSimpleName} and a ${f2.getClass.getSimpleName} should be different") {
        assert(f !== f2)
      }
    }
  }
