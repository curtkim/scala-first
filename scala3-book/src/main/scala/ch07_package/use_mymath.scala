package ch07_package

import ch07_package.{add, subtract, multiply}

@main def call_add() =
  println(add(1, 2))
  println(subtract(2, 1))
  println(multiply(1, 2))
