package data_types

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import cats.data.Kleisli

class KleisliTest extends AnyFlatSpec with should.Matchers {

  val parse: String => Option[Int] = s=> if (s.matches("-?[0-9]+")) Some(s.toInt) else None
  val reciprocal: Int => Option[Double] = i => if (i != 0) Some(1.0 / i) else None

  val parse2: Kleisli[Option, String, Int] =
    Kleisli((s: String) => if (s.matches("-?[0-9]+")) Some(s.toInt) else None)
  val reciprocal2: Kleisli[Option, Int, Double] =
    Kleisli((i: Int) => if (i != 0) Some(1.0 / i) else None)
  val parseAndReciprocal2: Kleisli[Option, String, Double] = reciprocal2.compose(parse2)

  "Kleisli" should ".." in {
    parseAndReciprocal2("5") should be (Some(0.2))
  }

}
