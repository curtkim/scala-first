package data_types

import org.scalatest
import org.scalatest.matchers.should
import org.scalatest.flatspec.AnyFlatSpec

import cats.free.FreeApplicative
import cats.free.FreeApplicative.lift
import cats.implicits._

import cats.Id
import cats.arrow.FunctionK



sealed abstract class ValidationOp[A]
case class Size(size: Int) extends ValidationOp[Boolean]
case object HasNumber extends ValidationOp[Boolean]


type Validation[A] = FreeApplicative[ValidationOp, A]

def size(size: Int) = lift(Size(size))
val hasNumber = lift(HasNumber)

val prog = (size(5), hasNumber).mapN { case (l, r) => l && r }

// a function that takes a string as input
type FromString[A] = String => A

val compiler = new FunctionK[ValidationOp, FromString] {
  def apply[A](fa: ValidationOp[A]): FromString[A] = str =>
    fa match {
      case Size(size) => str.size >= size
      case HasNumber => str.exists(c => "0123456789".contains(c))
    }
}


class FreeApplicativeTest extends AnyFlatSpec with should.Matchers {

  "validator" should "be" in {
    val validator = prog.foldMap[FromString](compiler)

    validator("1234") should be (false)
    validator("12345") should be (true)
    validator("abcd1") should be (true)
  }
}
