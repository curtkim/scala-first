package tio

import tio._

object Console {
  def putStrLn(str: => String) = TIO.effect(println(str))
}
