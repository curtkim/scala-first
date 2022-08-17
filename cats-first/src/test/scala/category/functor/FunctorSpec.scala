package category.functor

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

//import cats.implicits._
import cats.Functor
import cats.instances.option._
import cats.instances.list._


class FunctorSpec extends AnyFlatSpec with should.Matchers {

  "liftedFunc" should "success" in {
    val func = (x: Int) => x + 1
    val liftedFunc = Functor[Option].lift(func)
    liftedFunc(Option(1)) should be (Option(2))
  }

  "abstract over functor" should "" in {
    // 에러를 해결해야 된다.
    //def doMath[F[_]: Functor](start: F[Int]): F[Int] = start.map(n=> n+1)
    //doMath(Option(20))
    //doMath(List(1,2,3))
  }



}
