package state

import zio.*
import zio.Console.*

import java.io.IOException

case class Counter(value: Ref[Int]) {
  def inc: UIO[Unit] = value.update(_ + 1)
  def dec: UIO[Unit] = value.update(_ - 1)
  def get: UIO[Int] = value.get
}

object Counter {
  def make: UIO[Counter] = Ref.make(0).map(Counter(_))
}

object CounterSeq extends ZIOAppDefault {
  def run =
      for {
        c <- Counter.make
        _ <- c.inc
        _ <- c.inc
        _ <- c.dec
        _ <- c.inc
        v <- c.get
        _ <- ZIO.debug(s"This counter has a value of $v.")
      } yield ()
}

