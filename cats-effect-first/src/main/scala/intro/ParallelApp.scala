import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Utils {
  implicit class ShowThread[T](io: IO[T]) {
    def showThread: IO[T] = for {
      thunk <- io
      thread = Thread.currentThread.getName
      _ = println(s"[$thread] $thunk")
    } yield thunk
  }
}

import Utils.ShowThread

object ParallelApp extends IOApp {
  val tasks: List[IO[Int]] = (1 to 10).map(IO.pure).map(_.showThread).toList

  val incremented: IO[List[Int]] = tasks.parTraverse {
    ioi => for (i <- ioi) yield i + 1
  }

  val parallelOrNot = incremented.showThread

  override def run(args: List[String]): IO[ExitCode] = parallelOrNot.as(ExitCode.Success)
}
