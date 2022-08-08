package basic_concurrency

import zio.*
import zio.Console.*

import java.io.IOException

object Joining extends ZIOAppDefault {

  def run = myLogicPrint

  val myLogic =
    for {
      fiber <- ZIO.succeed("Hi!").fork
      message <- fiber.join
    } yield message

  val myLogicPrint = myLogic.flatMap(line => printLine(line))

}

