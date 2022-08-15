import zio.prelude.Validation

/*
sealed abstract case Class Event(timestamp: Long, description: String)

object Event{
  def make(timestamp: Long, description: String) : Validation[String, Event] =
    for {
      validTimestamp <- validateTimestamp(timestamp)
      validDescription <- validateDescription(description)
    } yield new Event(validTimestamp, validDescription)

  private def validateTimestamp(timestamp: Long) : Validation[String, Long] =
    if(timestamp >= 0 ) Validation.succeed(timestamp)
    else Validation.fail(s"Timestamp must not be nagative: $timestamp")

  private def validateDescription(description: String): Validation[String, String] =
    if(description.nonEmpty) Validation.succeed(description)
    else Validation.fail("Description must not be emtpy")
}


def collectEvents(rawEvents: List[(Int, String)]): Validation[String, List[Event]] =
  rawEvents.foldRight(Validation.succeed(List.empty): Validation[String, List[Event]]) {
    case ((timestamp, description), events) =>
      Event.make(timestamp, description).zipWith(events) {(event, events)=>
        event +: events
      }
  }
*/

class TraversableSpec {

}
