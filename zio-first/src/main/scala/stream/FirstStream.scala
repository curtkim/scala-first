package stream

import zio.Console.printLine
import zio.stream.ZStream
import zio.{Chunk, Task, ZIOAppDefault}

object FirstStream extends ZIOAppDefault {

  def run = result

  val result: Task[Unit] = ZStream.fromIterable(0 to 100).foreach(printLine(_))

}
