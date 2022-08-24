import cats.effect.{IO, IOApp}

object Hi_flatmap3 extends IOApp.Simple{
  val run = for {
    _ <- IO.println("Hello")
    _ <- IO.println("World")
  } yield ()
}
