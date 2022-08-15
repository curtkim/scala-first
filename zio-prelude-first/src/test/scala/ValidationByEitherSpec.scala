import org.scalatest._
import flatspec._
import org.scalatest.matchers.should

class ValidationByEitherSpec extends AnyFlatSpec with should.Matchers {

  case class Person(name: String, age: Int)

  def validateName(name: String): Either[String, String] =
    if (name.isEmpty) Left("Name was empty")
    else Right(name)

  def validateAge(age: Int): Either[String, Int] =
    if (age <= 0) Left(s"Age $age was less than zero")
    else Right(age)

  def validatePerson(name: String, age: Int): Either[String, Person] =
    for {
      name <- validateName(name)
      age <- validateAge(age)
    } yield Person(name, age)


  "validationByEither" should "success" in {
    validatePerson("curt", 20) should be (Right(Person("curt", 20)))
  }

  it should "fail" in {
    validatePerson("", -1) should be (Left("Name was empty"))
  }
}
