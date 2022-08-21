package tio3.example

import tio3.{TIO, TIOApp}

object Foreach10k extends TIOApp {
  def f = (i: Int) => TIO.effect(println(s"${i} ${Thread.currentThread.getName}"))

  def run = TIO.foreach(1 to 10000)(f) //i => TIO.effect(println(i)))

  def run3 = TIO.succeed(Vector())
    .flatMap(soFar => f(1).map(x => soFar :+ x))
    .flatMap(soFar => f(2).map(x => soFar :+ x))
    .flatMap(soFar => f(3).map(x => soFar :+ x))
}
