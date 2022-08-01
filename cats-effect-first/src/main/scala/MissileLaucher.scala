import cats.effect.{ExitCode, IO, IOApp}

object MissileLaucher extends IOApp {
  def putStr(str: String) : IO[Unit] = IO.delay(println(str))

  val launch = for {
    _ <- putStr("lauch missiles")
    _ <- putStr("lauch missiles")
  } yield ()

  override def run(args: List[String]) : IO[ExitCode] = launch.as(ExitCode.Success)
}
