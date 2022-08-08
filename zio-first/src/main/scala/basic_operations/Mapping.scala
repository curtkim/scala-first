package basic_operations

import zio.*
import zio.Console.*

object Mapping extends ZIOAppDefault {

  def run = myAppLogic

  val succeeded: ZIO[Any, Nothing, Int] = ZIO.succeed(21).map(_ * 2)
  val failed: ZIO[Any, Exception, Unit] =
    ZIO.fail("No no!").mapError(msg => new Exception(msg))
    
  val myAppLogic =
    for {
      value <- succeeded
      _ <- printLine(value)
    } yield ()
}

