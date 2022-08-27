package redis

import cats.effect.{ ExitCode, IO, IOApp }
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

/**
  * Provides an instance of `Log` given an instance of `Logger`.
  *
  * For simplicity and re-usability in all the examples.
  * */
trait LoggerIOApp extends IOApp {

  implicit val logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  def program: IO[Unit]

  override def run(args: List[String]): IO[ExitCode] =
    program.as(ExitCode.Success)

}

