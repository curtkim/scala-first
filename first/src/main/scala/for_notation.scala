@main def for_notation_main(): Unit ={
  val value = for {
    x <- Option(1)
    y <- Option("b")
    //z <- List(3, 4) // compile 되지 않는다.
  } yield {
    (x,y)
  }
  println(value)

  val b = for {
    i <- List(1, 2, 3)
    k <- List(4, 5)
    j <- Some(1)
    l <- Some(1)
  } yield (i, k, j, l)
  println(b)

  val c = for {
    i <- Map(1 -> 2)
    j <- Some(3)
  } yield j
  println(c)
}
