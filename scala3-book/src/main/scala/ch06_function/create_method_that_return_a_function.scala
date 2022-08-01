package ch06_function

def greet() =
  (name: String) => println(s"Hello, $name")

def greet(theGreeting: String) =
  (name: String) => println(s"$theGreeting, $name")


@main def call_greet() =
  val greetFunction = greet()
  greetFunction("kim")

  val hiFunction = greet("hi")
  hiFunction("kim")