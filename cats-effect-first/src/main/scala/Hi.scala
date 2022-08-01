import cats.effect.{IO, IOApp}

object Hi extends IOApp.Simple{
  val run =IO.println("Hello") >> IO.println("World")
}
