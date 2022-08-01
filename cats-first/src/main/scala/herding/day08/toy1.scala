package herding.day08

sealed trait Toy[+A, +Next]

object Toy{
  case class Output[A, Next](a: A, next:Next) extends Toy[A, Next]
  case class Bell[Next](next: Next) extends Toy[Nothing, Next]
  case class Done() extends Toy[Nothing, Nothing]
}

@main def toy1_main(): Unit ={
  Toy.Output('A', Toy.Done())

  Toy.Bell(Toy.Output('A', Toy.Done()))
}
