package basic_operations

import zio.*
import zio.Console.*

import java.io.IOException

object Chaining extends ZIOAppDefault {

  def run = sequenced

  val sequenced: ZIO[Any, IOException, Unit] =
    Console.readLine.flatMap(input => Console.printLine(s"You entered: $input"))

}

