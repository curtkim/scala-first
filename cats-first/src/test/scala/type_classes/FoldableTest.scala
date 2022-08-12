package type_classes

import cats.Foldable
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._


class FoldableTest extends AnyFlatSpec with should.Matchers {

  "Foldable[Option]" should "foldLeft" in {
    val fa = Option(1)
    Foldable[Option].foldLeft(fa, Option(0))((a, n) => a.map(_ + n)) should be (Option(1))
    fa.foldLeft(Option(0))((a,n)=>a.map(_+n)) should be (Option(1))
  }

  "Foldable[List]" should "foldLeft" in {
    List(1,2,3).foldLeft(0)((a,b)=> a+b) should be (6)
    Foldable[List].fold(List("a", "b", "c")) should be ("abc")
    Foldable[List].foldMap(List(1, 2, 4))(_.toString) should be ("124")
    Foldable[List].foldK(List(List(1, 2, 3), List(2, 3, 4))) should be (List(1,2,3,2,3,4))
  }
}
