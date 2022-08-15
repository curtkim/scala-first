package state

import zio.*
import zio.Console.*

import java.io.IOException
import zio._

object Sequential extends ZIOAppDefault {

  def run = action

  def inputNames: ZIO[Any, String, List[String]] = {
    def loop(names: List[String]): ZIO[Any, String, List[String]] = {
      Console.readLine("Please enter a name or `q` to exit: ").orDie.flatMap {
        case "q" =>  ZIO.succeed(names)
        case name => loop(names appended name)
      }
    }

    loop(List.empty[String])
  }

  val action =
    for {
      names <- inputNames
      _ <- Console.printLine(names)
    } yield ()
}

