package tio3

import scala.util.Try

sealed trait TIO[+A]{
  def flatMap[B](f: A=> TIO[B]): TIO[B] = TIO.FlatMap(this, f)
  def map[B](f: A=> B): TIO[B] = flatMap(a => TIO.succeed(f(a)))
  def recover[B >: A](f: Throwable => TIO[B]): TIO[B] = TIO.Recover(this, f)
  def *> [B](that: TIO[B]): TIO[B] = flatMap(_ => that)
}

object TIO {
  type AsyncDoneCallback[T] = Try[T] => Unit
  type AsyncTask[T] = AsyncDoneCallback[T] => Unit

  // Effect constructor
  case class Effect[+A](a: ()=> A) extends TIO[A]
  // Effect combinator
  case class FlatMap[A, B](tio: TIO[A], f: A=> TIO[B]) extends TIO[B]
  case class Fail[A](e: Throwable) extends TIO[A]
  case class Recover[A](tio: TIO[A], f: Throwable => TIO[A]) extends TIO[A]
  case class EffectAsync[+A](asyncTask: AsyncTask[A]) extends TIO[A]

  // constructor functions
  def succeed[A](a: A): TIO[A] = Effect(()=> a)
  def effect[A](a: =>A): TIO[A] = Effect(()=> a)
  def fail[A](throwable: Throwable): TIO[A] = Fail(throwable)
  def effectAsync[A](asyncTask: AsyncTask[A]): TIO[A] = EffectAsync(asyncTask)


  def foreach[A, B](xs: Iterable[A])(f: A => TIO[B]): TIO[Iterable[B]] =
    xs.foldLeft(TIO.succeed(Vector.empty[B]))((acc, curr) => for {
      soFar <- acc
      x <- f(curr)
    } yield soFar :+ x)
}
