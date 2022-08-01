package ch09_contextual_abstraction

trait Show[A]:
  def show(a: A): String

class ShowInt extends Show[Int]:
  def show(a: Int) = s"The number is ${a}!"

class SimpleShowInt extends Show[Int]:
  def show(a: Int) = s"${a}"

def toHtml[A](a: A)(aShow: Show[A]): String =
  "<p>" + aShow.show(a) + "</p>"

@main def type_classes_main(): Unit ={
  println(toHtml(42)(ShowInt()))
  println(toHtml(42)(SimpleShowInt()))
}
