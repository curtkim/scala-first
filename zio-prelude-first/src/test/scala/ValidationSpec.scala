import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.should

import zio.prelude.Validation
import zio.prelude.NonEmptyList


class ValidationSpec extends AnyFlatSpec with should.Matchers {

  case class Person(name: String, age: Int)

  def validateName(name: String): Validation[String, String] =
    if (name.isEmpty) Validation.fail("Name was empty")
    else Validation.succeed(name)

  def validateAge(age: Int): Validation[String, Int] =
    if (age <= 0) Validation.fail(s"Age $age was less than zero")
    else Validation.succeed(age)

  def validatePerson(name: String, age: Int): Validation[String, Person] =
    Validation.validateWith(validateName(name), validateAge(age))(Person)

  "validation" should "success" in {
    validatePerson("curt", 20) should be (Validation.succeed(Person("curt", 20)))
  }

  it should "fail" in {
    validatePerson("", -1) should be (Validation.fail("Name was empty, Age -1 was less than zero"))
  }
}
