
@main def list_monad(): Unit ={

  println(
    List(1,2,3).flatMap(it=> List.fill(it)(it))
  )

  val c: List[Int] = for {
    it <- List(1,2,3)
  } yield it
  println(c)
}