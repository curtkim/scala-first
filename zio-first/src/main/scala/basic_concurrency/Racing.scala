package basic_concurrency

import zio.*
import zio.Console.*

import java.io.IOException

object Racing extends ZIOAppDefault {

  def run = myLogicPrint

  val makeResult =
    for {
      winner <- ZIO.succeed("Hello").race(ZIO.succeed("Goodbye"))
    } yield winner

  val myLogicPrint = makeResult.flatMap(res=> printLine(res))
}

