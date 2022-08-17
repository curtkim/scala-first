package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global


class TraverseFutureSpec extends AnyFlatSpec with should.Matchers{

  val hostnames = List(
    "alpha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60) // just for demonstration

  val allUptimesHard: Future[List[Int]] =
    hostnames.foldLeft(Future(List.empty[Int])) {
      (accum, host) =>
        val uptime = getUptime(host)
        for {
          accum <- accum
          uptime <- uptime
        } yield accum :+ uptime
    }

  val allUptimes: Future[List[Int]] = Future.traverse(hostnames)(getUptime)

  "Future.traverse" should "success" in {
    Await.result(allUptimes, 1.second) should be (List(1020, 960, 840))
    Await.result(allUptimesHard, 1.second) should be (List(1020, 960, 840))
  }
}
