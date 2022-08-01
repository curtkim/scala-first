package ch08_type


object Logarithms:
  opaque type Logarithm = Double

  object Logarithm:
    def apply(d: Double): Logarithm = math.log(d)

  extension (x: Logarithm)
    def toDouble: Double = math.exp(x)
    def + (y: Logarithm): Logarithm = Logarithm(math.exp(x)+ math.exp(y))
    def * (y: Logarithm): Logarithm = x+y


@main def logarithm2_main(): Unit ={
  val l2 = Logarithm(2.0)
  val l3 = Logarithm(3.0)
  println((l2*l3).toDouble)
  println((l2+l3).toDouble)
}