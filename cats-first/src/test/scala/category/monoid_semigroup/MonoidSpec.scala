package category.monoid_semigroup

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

//import cats.implicits._
import cats.Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.semigroup._ // for |+|
import cats.instances.int._
import cats.instances.option._
import cats.instances.map._
import cats.instances.tuple._


class MonoidSpec extends AnyFlatSpec with should.Matchers {

  "StringMonoid" should "|+|" in {
    "Scala" |+| " with " |+| "Cats" should be ("Scala with Cats")
  }

  "OptionMonoid" should "|+|" in {
    Option(1) |+| Option(2) should be (Option(3))
  }

  "MapMonoid" should "|+|" in {
    val map1 = Map("a"->1, "b"->2)
    val map2 = Map("b"->3, "d"->4)
    map1 |+| map2 should be (Map("a"->1, "b"->5, "d"->4))
  }

  "TupleMonid" should "|+|" in {
    val tuple1 = ("Hello", 123)
    val tuple2 = ("World", 321)

    tuple1 |+| tuple2 should be (("HelloWorld", 444))
  }
}
