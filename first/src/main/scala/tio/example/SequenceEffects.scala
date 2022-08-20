package tio.example

import tio.{Console, TIOApp}

object SequenceEffects extends TIOApp {
  def run = {
    for {
      _ <- Console.putStrLn("first")
      _ <- Console.putStrLn("second")
    } yield ()
  }

}
