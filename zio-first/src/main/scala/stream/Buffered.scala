package stream

import zio.Console
import zio.Console.printLine
import zio.stream.ZStream
import zio.{Chunk, Task, ZIOAppDefault}
import zio.Schedule
import zio.*

object Buffered extends ZIOAppDefault {
  val s = ZStream
    .fromIterable(1 to 10)
    .rechunk(1)
    .tap(x => Console.printLine(s"before buffering: $x"))
    .buffer(4)
    .tap(x => Console.printLine(s"after buffering: $x"))
    .schedule(Schedule.spaced(1.second))

  def run = s.foreach(printLine(_))

}
