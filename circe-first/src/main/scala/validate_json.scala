import io.circe._, io.circe.parser._
import io.circe.generic.semiauto._
import io.circe.syntax._

val jsonString =
  """
    |{
    | "textField": "textContent",
    | "numericField": 123,
    | "booleanField": true,
    | "nestedObject": {
    |   "arrayField": [1, 2, 3]
    | }
    |}
    |""".stripMargin

case class Nested(arrayField: List[Int])

case class OurJson(
  textField: String,
  numericField: Int,
  booleanField: Boolean,
  nestedObject: Nested
)

implicit val nestedDecoder: Decoder[Nested] = deriveDecoder[Nested]
implicit val jsonDecoder: Decoder[OurJson] = deriveDecoder[OurJson]
implicit val jsonEncoder: Encoder[OurJson] = deriveEncoder[OurJson]


@main def validate_json_main(): Unit = {
  val parseResult: Either[ParsingFailure, Json] = parse(jsonString)

  parseResult match {
    case Left(parsingError) =>
      throw new IllegalArgumentException(s"Invalid JSON object: ${parsingError.message}")
    case Right(json) =>
      // \\ = findAllByKey function
      val numbers: List[Json] = json \\ "numericField"
      val firstNumber: Option[Option[JsonNumber]] =
        numbers.collectFirst { case field => field.asNumber }
      val singleOption: Option[Int] = firstNumber.flatten.flatMap(_.toInt)

      println(numbers)
      println(firstNumber)
      println(singleOption)
  }

  val decoded = decode[OurJson](jsonString)
  println(decoded)

  decoded match {
    case Right(decodedJson)=>
      val jsonObject: Json = decodedJson.asJson
      val newJsonString = jsonObject.spaces2
      println(newJsonString)
  }
}
