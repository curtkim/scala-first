package herding.day06
import cats._
import cats.data._
import cats.syntax.all._

def _gcd(a: Int, b:Int) : Int = {
  if(b == 0)
    a
  else
    _gcd(b, a%b)
}

def gcd(a: Int, b: Int) : Writer[List[String], Int] = {
  if( b == 0) for {
    _ <- Writer.tell(List("Finished with "+ a.show))
  } yield a
  else
    Writer.tell(List(s"${a.show} mod ${b.show} =${(a%b).show}")) >>= {_ =>
      gcd(b, a % b)
    }
}


@main def gcd_main(): Unit ={
  val (logs, result) = gcd(12, 16).run
  println(logs)
  println(result)

  println(_gcd(12, 16))
}