package ch09_contextual_abstraction

case class Circle(x: Double, y: Double, radius: Double)

object CircleHelpers:
  def circumference(c: Circle): Double = c.radius * math.Pi * 2

extension (c: Circle)
  def circumference: Double = c.radius * math.Pi * 2


@main def extension_main(): Unit = {
  val circle = Circle(2,3,5)

  println(CircleHelpers.circumference(circle))
  println(circle.circumference)
}