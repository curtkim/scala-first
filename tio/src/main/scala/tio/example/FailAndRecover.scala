package tio.example

import tio.{TIO, TIOApp}
import tio.Console.*

import scala.util.control.NonFatal

object FailAndRecover extends TIOApp{

  def run = {
    (for {
      _ <- putStrLn("first")
      _ <- TIO.fail(new RuntimeException)
      _ <- putStrLn("second")
    } yield ()).recover {
      case NonFatal(e) =>
        putStrLn(s"recover from failure: ${e.getClass.getName}")
    }
  }
}
