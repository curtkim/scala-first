package tio3

import java.time.Instant
import java.util.{Timer, TimerTask}
import scala.concurrent.duration.Duration
import scala.util.Success

object Clock {
  private val timer = new Timer("TIO-Timer", true)

  def sleep[A](duration: Duration): TIO[Unit] =
    TIO.effectAsync {onComplete =>
      timer.schedule(new TimerTask{
        override def run(): Unit = onComplete(Success(()))
      }, duration.toMillis)
    }
}
