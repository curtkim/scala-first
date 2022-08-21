package tio3

import tio3._

object Console {
  def putStrLn(str: => String) = TIO.effect(println(str))
}
