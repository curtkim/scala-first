package basic_operations

import zio.*
import zio.Console.*
import java.io.IOException

object Zipping extends ZIOAppDefault {

  def run = zippedPrint

  val zipped: ZIO[Any, Nothing, (String, Int)] =
    ZIO.succeed("4").zip(ZIO.succeed(2))

  val zippedPrint: IO[IOException, Unit] =
    zipped.flatMap((a, b)=> Console.printLine(s"$a $b"))

  val zipRight1 =
    Console.printLine("What is your name?").zipRight(Console.readLine)
}

