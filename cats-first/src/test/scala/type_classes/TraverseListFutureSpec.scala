package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.concurrent.{Await, Future}

import cats.Traverse
import cats.instances.future._
import cats.instances.list._


class TraverseListFutureSpec extends AnyFlatSpec with should.Matchers{

  val hostnames = List(
    "alpha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60) // just for demonstration


  "Future.traverse" should "success" in {
    val totalUptimes: Future[List[Int]] = Future.traverse(hostnames)(getUptime)
    val totalUptimes2: Future[List[Int]] = Traverse[List].traverse(hostnames)(getUptime)

    Await.result(totalUptimes, 1.second) should be (List(1020, 960, 840))
    Await.result(totalUptimes2, 1.second) should be (List(1020, 960, 840))
  }

  "Travese[List]" should "sequence" in {
    val numbers = List(Future(1), Future(2), Future(3))
    val numbers2 = Traverse[List].sequence(numbers)

    Await.result(numbers2, 1.second) should be (List(1,2,3))
  }
}
