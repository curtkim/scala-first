package basic_concurrency

import zio.*
import zio.Console.*

import java.io.IOException


// 어떻게 fib100Fiber에서 데이터를 꺼내나?
object Forking extends ZIOAppDefault {

  def run = myLogic

  def fib(n: Long): UIO[Long] =
    ZIO.suspendSucceed {
      if (n <= 1) ZIO.succeed(n)
      else fib(n - 1).zipWith(fib(n - 2))(_ + _)
    }

  val fib100Fiber: UIO[Fiber[Nothing, Long]] =
    for {
      fiber <- fib(100).fork
    } yield fiber


  val myLogic =
    for {
      message <- fib100Fiber
      _ <- printLine(s"Hello, ${message}, welcome to ZIO!")
    } yield message
}

