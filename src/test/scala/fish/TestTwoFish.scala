package fish

import model.fish.{CarnivorousFish, Fish, HerbivorousFish}

import org.scalatest.funsuite.AnyFunSuite

class TestTwoFish extends AnyFunSuite:
  val fishTypes: List[() => Fish] = List(
    () => HerbivorousFish(),
    () => CarnivorousFish()
  )

  fishTypes.foreach { i =>
    val f = i()
    fishTypes.foreach { j =>
      val f2 = j()
      test(s"A ${f.getClass.getSimpleName} and ${f2.getClass.getSimpleName} should have different names") {
        assert(f.name !== f2.name)
      }
    }
  }
