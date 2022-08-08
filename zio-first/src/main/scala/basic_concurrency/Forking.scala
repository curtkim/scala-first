package basic_concurrency

import zio.*
import zio.Console.*

import java.io.IOException

object Forking extends ZIOAppDefault {

  def run = fib100Fiber

  def fib(n: Long): UIO[Long] =
    ZIO.suspendSucceed {
      if (n <= 1) ZIO.succeed(n)
      else fib(n - 1).zipWith(fib(n - 2))(_ + _)
    }

  val fib100Fiber: UIO[Fiber[Nothing, Long]] =
    for {
      fiber <- fib(100).fork
    } yield fiber
}

