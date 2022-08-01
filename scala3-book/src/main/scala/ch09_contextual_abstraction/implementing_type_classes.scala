package ch09_contextual_abstraction

// Type class
trait Showable[A]:
  extension(a: A) def show: String

// normal trait
trait MyShow:
  def show: String


// Implement concrete instance
case class Person(firstName: String, lastName: String)

given Showable[Person] with
  extension(p: Person) def show: String =
    s"${p.firstName} ${p.lastName}"

def showAll[S: Showable](xs: List[S]): Unit =
  xs.foreach(x => println(x.show))

@main def implementing_type_classes_main(): Unit ={
  showAll(List(Person("Jane", "A"), Person("Mary", "B")))
}