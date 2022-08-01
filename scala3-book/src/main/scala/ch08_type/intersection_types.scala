package ch08_type

trait Resettable:
  def reset(): Unit

trait Growable[A]:
  def add(a: A): Unit

def f(x: Resettable & Growable[String]) : Unit =
  x.reset()
  x.add("first")
