import cats.effect.{IO, IOApp}
import scala.concurrent.duration.DurationInt

object Loop_Timeout extends IOApp.Simple {

  val loop: IO[Unit] = IO.println("Hello, World!") >> IO.sleep(100.millis) >> loop

  val run = loop.timeout(1.seconds)
}
