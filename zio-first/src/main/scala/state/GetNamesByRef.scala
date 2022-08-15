package state

import zio.*
import zio.Console.*

import java.io.IOException

object GetNamesByRef extends ZIOAppDefault {

  def run = action

  def getNames: ZIO[Any, String, List[String]] =
    for {
      ref <- Ref.make(List.empty[String])
      f1 <- Console
        .readLine("Please enter a name or 'q' to exit: ")
        .orDie
        .repeatWhileZIO {
          case "q"  => ZIO.succeed(false)
          case name => ref.update(_ appended name).as(true)
        }.fork
        f2 <- ZIO.foreachDiscard(Seq("John", "Jane", "Joe", "Tom")) { name =>
          ref.update(_ appended name) *> ZIO.sleep(1.second)
        }
        .fork
      _ <- f1.join
      _ <- f2.join
      v <- ref.get
    } yield v

  val action =
    for {
      names <- getNames
      _ <- Console.printLine(names)
    } yield ()
}

