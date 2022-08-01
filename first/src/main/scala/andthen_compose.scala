val f = (x: Float) => x.abs
val g = (x: Float) => x+3

val h1 = f andThen g
val h2 = g andThen f

val h3 = f compose g
val h4 = g compose f


@main def andthen_main(): Unit ={
  assert(h1(-1) == 4f)
  assert(h2(-1) == 2f)

  assert(h3(-1) == 2f)
  assert(h4(-1) == 4f)
}