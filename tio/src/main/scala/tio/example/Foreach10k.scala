package tio.example

import tio.TIOApp
import tio.TIO

object Foreach10k extends TIOApp {
  def f = (i: Int) => TIO.effect(println(i))

  def run0 = TIO.foreach(1 to 3)(f) //i => TIO.effect(println(i)))

  def run = TIO.succeed(Vector())
    .flatMap(soFar => f(1).map(x => soFar :+ x))
    .flatMap(soFar => f(2).map(x => soFar :+ x))
    .flatMap(soFar => f(3).map(x => soFar :+ x))
}
