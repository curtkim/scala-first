package tio.example

import tio.{TIO, Console, TIOApp}

object SequenceEffects extends TIOApp {
  def run: TIO[Unit] = {
    for {
      _ <- Console.putStrLn("first")
      _ <- Console.putStrLn("second")
    } yield ()
  }

}
