import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.should

/*
case Class Stats(counter1: Option[Sum[Long]], counter2: Option[Sum[Long]], counter3: Option[Sum[Long]])

object Stats {
  def make(counter1: Option[Long], counter2: Option[Long], counter3: Option[Long]): Stats =
    Stats(counter1.map(Sum(_)), counter2.map(Sum(_)), counter3.map(Sum(_)))

  implicit val associative: Associative[Stats] = new Associative[Stats] {
    def combine(l: => Stats, r: => Stats): Stats =
      Stats(l.counter1 <> r.counter1, l.counter2 <> r.counter2, l.counter3 <> r.counter3)
  }
}
*/
class StatCombineSpec extends AnyFlatSpec with should.Matchers {



}
