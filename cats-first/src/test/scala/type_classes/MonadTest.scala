package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.Monad

class MonadTest extends AnyFlatSpec with should.Matchers {

  "Monad" should "flatten" in {
    Option(Option(1)).flatten should be (Option(1))
    Option(None).flatten should be (None)
    List(List(1), List(2, 3)).flatten should be (List(1,2,3))
  }

}
