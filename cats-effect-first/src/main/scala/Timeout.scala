import cats.effect.{IO, IOApp}
import scala.concurrent.duration._

lazy val loop: IO[Unit] =
  IO.println("Hello World!")
  >> IO.sleep(1.second)
  >> loop

object Timeout extends IOApp.Simple {
  val run = loop.timeout(5.seconds)
}
