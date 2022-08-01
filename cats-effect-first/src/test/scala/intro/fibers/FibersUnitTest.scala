package intro.fibers

import cats.effect.IO
import cats.effect.kernel.Outcome

import munit.CatsEffectSuite
import scala.concurrent.duration._
import scala.util.Random

import intro.fibers.IOExtensions._


class FibersUnitTest extends CatsEffectSuite{

  test("Fiber should be canceled") {
    val io: IO[String] = IO("Starting a task").debug >> IO.sleep(400.millis) >> IO("Task completed").debug
    val fibCancel = for {
      fib <- io.start
      _ <- IO.sleep(100.millis) >> fib.cancel >> IO("Fiber cancelled").debug
      res <- fib.join
    } yield res

    fibCancel.map(outcome => assert(outcome.isCanceled))
  }

  test("fiber raised an exception") {
    val io = IO("Start Task") >> IO.sleep(100.millis) >> IO.raiseError[String](new RuntimeException("Task failed"))
    val fibRes = for {
      fib <- io.start
      res <- fib.join
    } yield res

    fibRes.map { outcome =>
      assert(outcome.isError)
    }
  }

  test("race 2 fibers") {
    val io1 = IO("Task 1") >> IO.sleep(Random.nextInt(500).millis) >> IO("Task 1 completed")
    val io2 = IO("Task 2") >> IO.sleep(550.millis) >> IO("Task 2 completed")
    val raceResult = IO.race(io1, io2)
    assertIO(raceResult, Left("Task 1 completed"))
  }
}
