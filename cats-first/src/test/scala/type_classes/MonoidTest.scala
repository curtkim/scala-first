package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

//import cats.implicits._
import cats.Monoid

def combineAll[A: Monoid](as: List[A]) =
  as.foldLeft(Monoid[A].empty)(Monoid[A].combine)


class MonoidTest extends AnyFlatSpec with should.Matchers {

  "combineAll" should "foldLeft" in {
    combineAll(List(1, 2, 3)) should be (6)
    combineAll(List("hello", " ", "world")) should be ("hello world")
    //인상적이다. map의 value를 더했다.
    combineAll(List(Map('a' -> 1), Map('a' -> 2, 'b' -> 3), Map('b' -> 4, 'c' -> 5))) should be (Map('a'->3, 'b'->7, 'c'->5))
    combineAll(List(Set(1, 2), Set(2, 3, 4, 5))) should be (Set(1,2,3,4,5))
  }

}
