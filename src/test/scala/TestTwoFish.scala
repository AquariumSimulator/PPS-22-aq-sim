import org.scalatest.funspec.AnyFunSpec
import org.scalatest.BeforeAndAfterEach

import model.Fish

class TestTwoFish extends AnyFunSpec with BeforeAndAfterEach:

  var f: Fish = Fish()
  var f2: Fish = Fish()

  override def beforeEach(): Unit =
    f = Fish()
    f2 = Fish()

  describe("Two Fish") {
    they("should have different names") {
      assert(f.name != f2.name)
    }
  }
