import cats.kernel.Semigroup
import cats.instances.int._

//implicit val multiplicationSemigroup = new Semigroup[Int]{
//  override def combine(x: Int, y:Int): Int = x*y
//}

@main def semigroup_main(): Unit = {
  val onePlusTwo: Int = Semigroup[Int].combine(1,2)
  println(onePlusTwo)

}