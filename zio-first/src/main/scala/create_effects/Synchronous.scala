package create_effects

import zio.{UIO, ZIO, ZIOAppDefault}

import java.io.IOException
import scala.io.StdIn

object Synchronous extends ZIOAppDefault {

  def run = myAppLogic

  val readLine2: ZIO[Any, Throwable, String] = ZIO.attempt(StdIn.readLine())
  def printLine2(line: String): UIO[Unit] = ZIO.succeed(println(line))
  
  val readLine3: ZIO[Any, IOException, String] =
    ZIO.attempt(StdIn.readLine()).refineToOrDie[IOException]

  val myAppLogic =
    for {
      _ <- printLine2("Hello! What is your name?")
      name <- readLine2
      _ <- printLine2(s"Hello, ${name}, welcome to ZIO!")
    } yield ()
}
