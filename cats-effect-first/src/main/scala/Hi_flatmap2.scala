import cats.effect.{IO, IOApp}

object Hi_flatmap2 extends IOApp.Simple{
  val run = IO.println("Hello").flatMap { _ =>
    IO.println("World")
  }
}
