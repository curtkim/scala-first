package withcats

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.instances.vector._   // for Monoid
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._ // for tell

import cats.data.Writer
import cats.data.WriterT

class WriterSpec extends AnyFlatSpec with should.Matchers {

  type Logged[A] = Writer[Vector[String], A]

  "Writer" should "pure" in {
    123.pure[Logged] should be (Writer(Vector(), 123))
  }
  it should "tell" in {
    Vector("msg1", "msg2", "msg3").tell should be (Writer(Vector("msg1", "msg2", "msg3"), ()))
  }
  it should "apply and writer syntax" in {
    val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
    val b = 123.writer(Vector("msg1", "msg2", "msg3"))

    a should be (b)
    a.value should be (123)
    a.written should be (Vector("msg1", "msg2", "msg3"))
  }

  it should "run" in {
    val b = 123.writer(Vector("msg1", "msg2", "msg3"))
    val (log, result) = b.run

    result should be (123)
    log should be (Vector("msg1", "msg2", "msg3"))
  }

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

  it should "flatMap" in {
    writer1.run should be (Vector("a","b","c","x","y","z"), 42)
  }
  it should "mapWritten" in {
    writer1.mapWritten(_.map(_.toUpperCase)) should be (Writer(Vector("A","B","C","X","Y","Z"), 42))
  }
  it should "bimap" in {
    writer1.bimap(
      log => log.map(_.toUpperCase),
      res => res * 100
    ) should be (Writer(Vector("A","B","C","X","Y","Z"), 4200))
  }
  it should "reset" in {
    writer1.reset should be (Writer(Vector(), 42))
  }
  it should "swap" in {
    writer1.swap should be (Writer(42, Vector("a","b","c","x","y","z")))
  }
}
