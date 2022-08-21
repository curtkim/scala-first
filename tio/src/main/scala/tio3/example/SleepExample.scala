package tio3.example

import java.time.Instant
import tio3.{TIOApp, TIO}
import tio3.Clock._
import scala.concurrent.duration.Duration
import scala.concurrent.duration.DurationInt


object SleepExample extends TIOApp{
  def run = {
    for {
      _ <- TIO.effect(println(s"[${Instant.now}] running first effect on ${Thread.currentThread.getName}"))
      _ <- sleep(2.seconds)
      _ <- TIO.effect(println(s"[${Instant.now}] running second effect on ${Thread.currentThread.getName}"))
    } yield ()
  }
}
