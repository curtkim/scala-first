package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

//import cats.Traverse.nonInheritedOps.toTraverseOps
//import cats.Traverse.ops.toAllTraverseOps
//import cats.implicits.toTraverseOps
//import cats.syntax.all.toTraverseOps
//import cats.syntax.traverse.toTraverseOps
import cats.implicits._


class TraverseTest extends AnyFlatSpec with should.Matchers {

  "List[Some]" should "sequence" in {
    val list = List(Some(1), Some(2), Some(3))
    list.sequence should be(Some(List(1, 2, 3)))
  }

  "A note on sequencing" should "..." in {
    val list = List(Some(1), Some(2), None)
    list.traverse(identity) should be (None)
    list.sequence should be (None)
  }


  /*
  "traverse" should "." in {
    val list = List(Some(1), Some(2), Some(3))
    list.traverse(identity) should be(None)
  }
  */
}
