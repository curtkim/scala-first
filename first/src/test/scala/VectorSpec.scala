import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.immutable.Vector

class VectorSpec extends AnyFlatSpec with should.Matchers {

  "Vector" should ":+" in {
    val vectorEmpty = Vector.empty
    vectorEmpty :+ 1 :+ 2 :+ 3 should be (Vector(1,2,3))
  }

}
