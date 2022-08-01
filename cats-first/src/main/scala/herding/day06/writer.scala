package herding.day06

import cats._, cats.data._, cats.syntax.all._

def logNumber(x: Int): Writer[List[String], Int] =
  Writer(List("Got number:" + x.show), 3)

def multiWithLog: Writer[List[String], Int] =
  for {
    a <- logNumber(3)
    b <- logNumber(5)
  } yield a * b

@main def writer_main() = {
  val (logs, result) = multiWithLog.run
  println(logs)
  println(result)
}