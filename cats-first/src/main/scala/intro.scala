// Type class
trait Area[A] {
  def area(a: A): Double
}

// Type Class Instance
case class Rectangle(width: Double, length: Double)
case class Circle(radius: Double)


object AreaInstance{
  implicit val rectangleArea: Area[Rectangle] = new Area[Rectangle]{
    def area(a: Rectangle): Double = a.width * a.length
  }

  implicit val circleArea: Area[Circle] = new Area[Circle]{
    def area(a: Circle): Double = Math.PI * (a.radius * a.radius)
  }
}

// Interface
object ShapeArea {
  def areaOf[A](a: A)(implicit shape: Area[A]): Double = shape.area(a)
}

@main def main(): Unit ={
  val rect = Rectangle(100, 200)

  val area: Double = ShapeArea.areaOf(rect)(AreaInstance.rectangleArea)
  println(area)
}