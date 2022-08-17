package pure

import zio.prelude.fx.ZPure

//             Writer   State State R    E        A
val one: ZPure[Nothing, Unit, Unit, Any, Nothing, Int] = ZPure.succeed(1)
val two = one.map(_ + 1)
val three = one.zipWith(two)(_+_)

val six =
  for {
    x <- one
    y <- two
    z <- three
  } yield x+y+z

val fail = ZPure.fail("fail")

def parseIntThrowable(s: String) : ZPure[Nothing, Unit, Unit, Any, Throwable, Int] =
  ZPure.attempt(s.toInt)

def parseInt(s: String): ZPure[Nothing, Unit, Unit, Any, String, Int] =
  parseIntThrowable(s).mapError(_.getMessage)

def parseIntOrDefault(s: String) = parseInt(s).catchAll(_ => ZPure.succeed(0))


@main def pure1_main(): Unit ={
  val value: Int = three.run
  println(value)
}
