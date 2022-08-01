package ch08_type

import scala.annotation.targetName

class Logarithm(protected val underlying: Double):
  def toDouble: Double = math.exp(underlying)
  def + (that: Logarithm) : Logarithm =
    Logarithm(this.toDouble + that.toDouble)

  def *(that: Logarithm): Logarithm =
    new Logarithm(this.underlying + that.underlying)

object Logarithm:
  def apply(d: Double): Logarithm = new Logarithm(math.log(d))


@main def logarithm_main(): Unit ={
  val l2 = Logarithm(2.0)
  val l3 = Logarithm(3.0)
  println((l2*l3).toDouble)
  println((l2+l3).toDouble)
}