val f1 = (x: Int, y: Int) => (x+1, y+2)
val g1 = (x: Int, y: Int) => x-y

val h8 = f1.tupled andThen g1.tupled
val h9 = g1.tupled compose f1.tupled

@main def binary_andThen_main(): Unit = {
  assert(h8((5,4)) == 0)
  assert(h9((5,4)) == 0)
}