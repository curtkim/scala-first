import cats.effect.{IO, IOApp}

object flatmap_convert extends IOApp.Simple {
  val run1 = IO.pure(1)
    .flatMap { it => IO.pure(it + " " + it) }
    .flatMap { it => IO.println(it) }

  val run2 = for {
    a <- IO.pure(1)
    b <- IO.pure(a + " " + a)
    _ <- IO.println(b)
  } yield ()

  val run = run1
}
