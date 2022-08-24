import cats.effect.{IO, IOApp}

object Hi_flatmap extends IOApp.Simple{
  val run = IO.println("Hello") >> IO.println("World") *> IO.println("oh!")
}
