package tio3

import scala.util.Try
import scala.util.Failure
import scala.util.Success
import tio3.TIO

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.Duration

trait Runtime {

  def unsafeRunAsync[A](tio: TIO[A])(callback: Try[A] => Unit): Unit

  // Run tio and return Success/Failure
  def unsafeRunSync[A](tio: TIO[A], timeout: Duration = Duration.Inf): Try[A] =
    Await.ready(unsafeRunToFuture(tio), timeout).value.get
  def unsafeRunToFuture[A](tio: TIO[A]): Future[A] = {
    val promise = Promise[A]()
    unsafeRunAsync(tio)(promise.tryComplete)
    promise.future
  }
}

// interpreter
object Runtime extends Runtime {
  private val executor = Executor.fixed(16, "tio-default")

  override def unsafeRunAsync[A](tio: TIO[A])(callback: Try[A] => Unit): Unit = {
    eval(tio)(callback.asInstanceOf[Try[Any] => Unit])
  }

  private def eval(tio: TIO[Any])(done: Try[Any] => Unit): Unit = {
    executor.submit {
      tio match {
        case TIO.Effect(a) =>
          done(Try(a()))

        case TIO.EffectAsync(callback) => callback(done)

        case TIO.FlatMap(tio, f: (Any => TIO[Any])) =>
          eval(tio) {
            case Success(res) => eval(f(res))(done)
            case Failure(e) => done(Failure(e))
          }

        case TIO.Fail(e) => done(Failure(e))

        case TIO.Recover(tio, f) =>
          eval(tio) {
            case Failure(e) => eval(f(e))(done)
            case success => done(success)
          }
        // >>> Handling EffectAsync
        case TIO.EffectAsync(callback) =>
          callback(done)
      }
    }
  }
}

trait TIOApp {
  def run: TIO[Any]
  final def main(args: Array[String]): Unit = {
    Runtime.unsafeRunSync(run).get
  }
}
