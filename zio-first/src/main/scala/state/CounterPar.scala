package state

import zio.*
import zio.Console.*

import java.io.IOException

import state.Counter


object CounterPar extends ZIOAppDefault {
  def run =
    for {
      c <- Counter.make
      _ <- c.inc <&> c.inc <&> c.dec <&> c.inc
      v <- c.get
      _ <- ZIO.debug(s"This counter has a value of $v.")
    } yield ()
}

