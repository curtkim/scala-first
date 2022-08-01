package herding.day00

case class Car(make: String)

// 1. Parametric polymorphism
def head[A](xs: List[A]) = xs(0)


// 2. Subtype polymorphism
trait PlusIntf[A]{
  def plus(a2: A): A
}
def plusBySubtype[A <: PlusIntf[A]](a1: A, a2: A) = a1.plus(a2)

// 3. Ad-hoc polymorphism
trait CanPlus[A]{
  def plus(a1: A, a2: A): A
}


@main def polymorphism_main(): Unit ={
  println(head(1 :: 2 :: Nil))
  println(head(Car("Civic") :: Car("CR-V") :: Nil))
}