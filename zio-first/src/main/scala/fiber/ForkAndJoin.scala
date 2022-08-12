package fiber

import zio.*
import zio.Console.*

object ForkAndJoin extends ZIOAppDefault {

  def run =
    for {
      fiber <- (ZIO.sleep(3.seconds) *>
        printLine("Hello, after 3 second") *>
        ZIO.succeed(10)).fork
      _ <- printLine(s"Hello, World!")
      res <- fiber.join
      _ <- printLine(s"Our fiber succeeded with $res")
    } yield ()

}
