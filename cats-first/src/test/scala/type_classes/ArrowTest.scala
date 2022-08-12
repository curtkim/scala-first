package type_classes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.arrow.Arrow
import cats.implicits._


class ArrowTest extends AnyFlatSpec with should.Matchers {

  "meanAndVar" should "..." in {
    def combine[F[_, _] : Arrow, A, B, C](fab: F[A, B], fac: F[A, C]) =
      Arrow[F].lift((a: A) => (a, a)) >>> (fab *** fac)

    val mean = combine((_: List[Int]).sum, (_: List[Int]).size) >>> { case (x, y) => x.toDouble / y }
    val variance = combine(((_: List[Int]).map(x => x * x)) >>> mean, mean) >>> { case (x, y) => x - y * y }
    val meanAndVar = combine(mean, variance)

    meanAndVar(List(1, 2, 3, 4)) should be ((2.5, 1.25))


    val mean2: List[Int] => Double = xs => xs.sum.toDouble / xs.size
    val variance2: List[Int] => Double = xs => mean2(xs.map(x => x * x)) - scala.math.pow(mean2(xs), 2.0)
    var meanAndVar2 = combine(mean2, variance2)
    meanAndVar2(List(1, 2, 3, 4)) should be ((2.5, 1.25))
  }

}
