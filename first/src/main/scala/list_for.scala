
@main def list_for_main(): Unit = {

  val myList = List(1, 2, 3)

  for (element <- myList) {
    println(element)
  }

  val moduled = for (i <- myList) yield i % 2
  println(moduled)

//  for {
//    i <- myList
//    _ <- Console.println(i) // value map is not a member of Unit
//  } yield ()

}
