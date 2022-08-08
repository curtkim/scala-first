package basic_concurrency

import zio.*
import zio.Console.*

import java.io.IOException

object Composing extends ZIOAppDefault {

  def run = myLogicPrint

  val makeTuple =
    for {
      fiber1 <- ZIO.succeed("Hi!").fork
      fiber2 <- ZIO.succeed("Bye!").fork
      fiber = fiber1.zip(fiber2)
      tuple <- fiber.join
    } yield tuple

  val myLogicPrint = makeTuple.flatMap(tuple => printLine(tuple))
}

