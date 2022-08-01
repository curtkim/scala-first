package herding.day07

import cats.data.Validated
import cats._
import cats.syntax.all._
import Validated.{Valid, Invalid}

@main def validated_main(): Unit ={
  println(Valid[String]("event 1 ok"))
  println(Invalid[String]("event 1 failed"))

//  val result = (Valid[String]("event 1 ok"),
//    Invalid[String]("event 2 failed!"),
//    Invalid[String]("event 3 failed!")) mapN {
//    _ + _ + _
//  }
//  println(result)

}