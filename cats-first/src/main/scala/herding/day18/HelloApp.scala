package herding.day18

import cats._, cats.syntax.all._
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object HelloApp extends App {
  val program = for {
    _ <- IO.print("What's your name? ")
    x <- IO.readLine
    _ <- IO.println(s"Hello, $x")
  } yield ()

  program.unsafeRunSync()
}
