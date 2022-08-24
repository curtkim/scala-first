package withcats

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import cats.data.Reader


class ReaderSpec extends AnyFlatSpec with should.Matchers {

  final case class Cat(name: String, favoriteFood: String)

  val catName: Reader[Cat, String] = Reader(cat => cat.name)

  "Reader" should "" in {
    catName.run( Cat("Garfield", "lasagne")) should be ("Garfield")
  }

  val greetKitty: Reader[Cat, String] = catName.map(name => s"Hello ${name}")
  "map" should "combine" in {
    greetKitty.run(Cat("Heathcliff", "junk food")) should be ("Hello Heathcliff")
  }


  val feedKitty: Reader[Cat, String] = Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")
  val greetAndFeed: Reader[Cat, String] =
    for {
      // It allows us to combine readers that depend on the same input type
      greet <- greetKitty
      feed  <- feedKitty
    } yield s"$greet. $feed."

  "flatMap" should "combine" in {
    greetAndFeed(Cat("Garfield", "lasagne")) should be ("Hello Garfield. Have a nice bowl of lasagne.")
  }
}
