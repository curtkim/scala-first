package stream

import zio.Console.printLine
import zio.stream.ZStream
import zio.{Chunk, Task, ZIOAppDefault}

object Chunked extends ZIOAppDefault {

  def run = s2

  val s2 = ZStream.fromChunks(Chunk(1, 2, 3), Chunk(4, 5, 6)).foreach(printLine(_))

}
