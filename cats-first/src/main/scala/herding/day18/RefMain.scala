package herding.day18

import cats._, cats.syntax.all._
import cats.effect.{IO, Ref}
import cats.effect.unsafe.implicits._

def e1 = for {
  r <- Ref[IO].of(0)
  _ <- r.update(_ + 1)
} yield r

def e2 = for {
  r <- e1
  x <- r.get
} yield x

@main def ref_main(): Unit ={
  val result = e2.unsafeRunSync()
  println(result)
}
