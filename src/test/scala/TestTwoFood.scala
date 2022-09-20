import model.FeedingType
import model.food.Food
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random

class TestTwoFood extends AnyFunSuite:
  val fishTypes: List[() => Food] = List(
    () =>
      Food(feedingType = FeedingType.HERBIVOROUS, position = (Random.between(0.0, 10.0), Random.between(0.0, 10.0))),
    () => Food(feedingType = FeedingType.CARNIVOROUS, position = (Random.between(0.0, 10.0), Random.between(0.0, 10.0)))
  )

  fishTypes.foreach { i =>
    val f = i()
    fishTypes.foreach { j =>
      val f2 = j()
      test(s"A ${f.feedingType} food and a ${f2.feedingType} food should be different") {
        assert(f !== f2)
      }
    }
  }
