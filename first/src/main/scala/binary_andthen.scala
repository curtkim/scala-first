val f = (x: Int, y: Int) => (x+1, y+2)
val g = (x: Int, y: Int) => x-y

val h = f.tupled andThen g.tupled
val h2 = g.tupled compose f.tupled

@main def binary_andThen_main(): Unit = {
  assert(h((5,4)) == 0)
  assert(h2((5,4)) == 0)
}