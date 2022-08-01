package herding.day08

import cats.{Functor, Show}
import cats.free.Free
import herding.day08


sealed trait CharToy[+Next]

object CharToy {
  case class CharOutput[Next](a: Char, next: Next) extends CharToy[Next]

  case class CharBell[Next](next: Next) extends CharToy[Next]

  case class CharDone() extends CharToy[Nothing]

  implicit val charToyFunctor: Functor[CharToy] = new Functor[CharToy] {
    def map[A, B](fa: CharToy[A])(f: A => B): CharToy[B] = fa match {
      case o: CharOutput[A] => CharOutput(o.a, f(o.next))
      case b: CharBell[A] => CharBell(f(b.next))
      case CharDone() => CharDone()
    }
  }

  def output(a: Char): Free[CharToy, Unit] =
    Free.liftF[CharToy, Unit](CharOutput(a, ()))

  def bell: Free[CharToy, Unit] = Free.liftF[CharToy, Unit](CharBell(()))

  def done: Free[CharToy, Unit] = Free.liftF[CharToy, Unit](CharDone())

  def pure[A](a: A): Free[CharToy, A] = Free.pure[CharToy, A](a)
}


import CharToy._

def showProgram[R: Show](p: Free[CharToy, R]): String =
  p.fold({ (r: R) => "return " + Show[R].show(r) + "\n" },
    {
      case CharOutput(a, next) =>
        "output " + Show[Char].show(a) + "\n" + showProgram(next)
      case CharBell(next) =>
        "bell " + "\n" + showProgram(next)
      case CharDone() =>
        "done\n"
    })

@main def char_toy_main(): Unit = {

  val subroutine = output('A')
  val program = for {
    _ <- subroutine
    _ <- bell
    _ <- done
  } yield ()

  //println(program)
  println(showProgram(program))

  println(showProgram(
    pure('A') flatMap output
  ))

  println(showProgram(
    (output('A') flatMap {_ => done}) flatMap {_ => output('C')}
  ))
}