import io.circe.syntax._

@main def as_json_main(): Unit ={
  val intsJson = List(1,2,3).asJson
  println(intsJson)
}
