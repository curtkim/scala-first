package herding.day18

import cats._, cats.syntax.all._
import cats.effect.{ExitCode, IO, IOApp}

object HelloIOApp extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    program.as(ExitCode.Success)

  lazy val program = for {
    _ <- IO.print("What's your name? ")
    x <- IO.readLine
    _ <- IO.println(s"Hello, $x")
  } yield ()
}
