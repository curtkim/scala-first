package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import cats.Functor
import cats.implicits._
import cats.data.Nested

class FunctorTest extends AnyFlatSpec with should.Matchers {
  val listOption = List(Some(1), None, Some(2))

  "Functors" should "compose" in {
    // Through Functor#compose
    Functor[List].compose[Option].map(listOption)(_ + 1) should be (List(Some(2), None, Some(3)))
  }
 
  it should "compose by nested" in {
    val nested = Nested(listOption)
    nested.map(_ + 1) should be(Nested(List(Some(2), None, Some(3))))
  } 
  

}
