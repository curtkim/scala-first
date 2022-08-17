package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import cats.Applicative

class ApplicativeTest extends AnyFlatSpec with should.Matchers {

  "Compose" should "..." in {
    import cats.data.Nested
    import cats.implicits._
    import scala.concurrent.Future
    import scala.concurrent.ExecutionContext.Implicits.global

    val x = Future.successful(Some(5))
    val y = Future.successful(Some('a'))
    //Applicative[Future].compose[Option].map2(x, y)(_ + _) should be (Future.successful(Some(5+'a')))

    //val nested = Applicative[Nested[Future, Option, *]].map2(Nested(x), Nested(y))(_ + _)
  }

  "Traverse" should "map3" in {
    import java.sql.Connection

    val username = Some("username")
    val password = Some("password")
    val url = Some("some.login.url.here")

    // Stub for demonstration purposes
    def attemptConnect(username: String, password: String, url: String) = None

    Applicative[Option].map3(username, password, url)(attemptConnect) should be (Some(None))
  }

  "TraverseOption" should "..." in {
    def traverseOption[A, B](as: List[A])(f: A => Option[B]) =
      as.foldRight(Some(List.empty[B]): Option[List[B]]) { (a: A, acc: Option[List[B]]) =>
        val optB = f(a)
        // optB and acc are independent effects so we can use Applicative to compose
        Applicative[Option].map2(optB, acc)(_ :: _)
      }

    traverseOption(List(1, 2, 3))(i => Some(i): Option[Int]) should be (Some(List(1, 2, 3)))
  }
}
