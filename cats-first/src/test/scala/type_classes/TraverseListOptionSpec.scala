package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.Traverse
import cats.instances.option._
import cats.instances.list._



class TraverseListOptionSpec extends AnyFlatSpec with should.Matchers{

  def my_identity(it: Option[Int]): Option[Int] = it

  val list = List(Some(1), Some(2), Some(3))

  "Traverse[List[Option]]" should "traverse" in {

    Traverse[List].traverse(list)(my_identity) should be (Some(List(1,2,3)))

    val my_identity2 = (it: Option[Int]) => it
    Traverse[List].traverse(list)(my_identity2) should be (Some(List(1,2,3)))

    Traverse[List].traverse(list)((it: Option[Int])=>it) should be (Some(List(1,2,3)))
    Traverse[List].traverse(list)(it=>it: Option[Int]) should be (Some(List(1,2,3)))
  }

  "Traverse[List[Int]]" should "traverse" in {
    Traverse[List].traverse(List(1,2,3))(i => Some(i): Option[Int]) should be (Some(List(1,2,3)))
  }

  /*
  it should "sequence" in {
    Traverse[List].sequence(list) should be (Some(List(1,2,3)))
  }
  */
}
