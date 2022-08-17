package pure

import zio.prelude.fx.ZPure
import zio.prelude.fx.Cause


case class Person(Name: String, age:Int)

def validateName(name:String) :ZPure[Nothing, Unit, Unit, Any, String, String] =
  if (name.isEmpty) ZPure.fail("name was empty")
  else ZPure.succeed(name)

def validateAge(age: Int) : ZPure[Nothing, Unit, Unit, Any, String, Int] =
  if (age < 0) ZPure.fail(s"Age $age was less than zero")
  else ZPure.succeed(age)

def validatePerson(name: String, age: Int): ZPure[Nothing, Unit, Unit, Any, String, Person] =
  validateName(name).zipWith(validateAge(age))(Person)

def validatePersonCause(name: String, age: Int) =
  validatePerson(name, age).sandbox
  