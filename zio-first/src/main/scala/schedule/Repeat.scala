package schedule

import zio.*
import zio.Console.*

import java.io.IOException

object Repeat extends ZIOAppDefault {

  def run = intervaled

  val action: ZIO[Any, IOException, Unit] = Console.printLine(s"hello world")

  val repeated = action repeat Schedule.recurs(2)

  val intervaled = action repeat Schedule.spaced(1.seconds)
}

