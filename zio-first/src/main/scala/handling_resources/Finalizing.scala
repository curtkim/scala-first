package handling_resources

import zio.*
import zio.Console.*

import java.io.IOException

object Finalizing extends ZIOAppDefault {

  def run = finalized

  val finalizer: UIO[Unit] =
    ZIO.succeed(println("Finalizing!"))
  // finalizer: UIO[Unit] = Sync(
  //   trace = "repl.MdocSession.App.finalizer(handling_resources.md:15)",
  //   eval = <function0>
  // )

  val finalized: IO[String, Unit] =
    ZIO.fail("Failed!").ensuring(finalizer)
  // finalized: IO[String, Unit] = Dynamic(
  //   trace = "repl.MdocSession.App.finalized(handling_resources.md:19)",
  //   update = 1L,
  //   f = zio.ZIO$$$Lambda$10322/881585174@560a212c
  // )
}

