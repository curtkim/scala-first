import zio._

object ZLayerTest extends zio.ZIOAppDefault {
  trait Logging {
    def log(line: String): UIO[Unit]
  }

  object Logging {
    def log(line: String): URIO[Logging, Unit] =
      ZIO.serviceWithZIO[Logging](_.log(line))
  }

  case class LoggingLive(ref: Ref[Long]) extends Logging {
    override def log(line: String): UIO[Unit] = for {
      current <- Clock.currentDateTime
      count <- ref.modify(x => (x + 1, x + 1))
      _ <- Console.printLine(current.toString + s"\t[$count]\t" + line).orDie
    } yield ()
  }

  object LoggingLive {
    val layer: ZLayer[Ref[Long], Nothing, LoggingLive] = ZLayer.fromFunction(LoggingLive.apply _)

    //    val layer: ZLayer[Ref[Long], Nothing, LoggingLive] =
    //      ZLayer {
    //        for {
    //          ref <- ZIO.service[Ref[Long]]
    //        } yield LoggingLive(ref)
    //      }
  }

  val program: ZIO[Logging, Nothing, Unit] = Logging.log("Hi") *> Logging.log("Hello")

  val refLayer: ZLayer[Any, Nothing, Ref[Long]] = ZLayer(Ref.make(0L))

  def run = program.provide(LoggingLive.layer, refLayer)
}

