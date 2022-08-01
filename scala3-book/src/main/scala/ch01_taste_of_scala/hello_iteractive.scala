package ch01_taste_of_scala

import scala.io.StdIn.readLine

@main def helloInteractive() =
  println("enter your name: ")
  val name = readLine()
  println("Hello "+ name)
