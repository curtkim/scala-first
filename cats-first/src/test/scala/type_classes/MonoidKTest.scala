package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.MonoidK

class MonoidKTest extends AnyFlatSpec with should.Matchers {

  "MonoidK" should "combineK, which also takes one type parameter" in {
    MonoidK[List].combineK[String](List("hello", "world"), List("goodbye", "moon")) should be (List("hello", "world", "goodbye", "moon"))
    MonoidK[List].combineK[Int](List(1, 2), List(3, 4)) should be (List(1,2,3,4))
  }

  it should "Actually the type parameter can usually be inferred" in {
    MonoidK[List].combineK(List("hello", "world"), List("goodbye", "moon")) should be (List("hello", "world", "goodbye", "moon"))
    MonoidK[List].combineK(List(1, 2), List(3, 4)) should be (List(1,2,3,4))
  }

}
