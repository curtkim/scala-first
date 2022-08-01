package herding.day07

import cats.data.State

type Stack = List[Int]

val pop = State[Stack, Int] {
  case x :: xs => (xs, x)
  case Nil     => sys.error("stack is empty")
}

def push(a: Int) = State[Stack, Unit] {
  case xs => (a :: xs, ())
}

def stackManip: State[Stack, Int] = for {
  _ <- push(3)
  a <- pop
  b <- pop
} yield(b)

@main def state_stack_main(): Unit ={
  println(stackManip.run(List(5,8,2,1)).value)
}