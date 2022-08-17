import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

trait Semigroup[T]:
  extension (t: T)
    def combine(other: T): T
    def <+>(other: T): T = t.combine(other)

trait Monoid[T] extends Semigroup[T]:
  def unit: T


given StringMonoid: Monoid[String] with
  def unit: String = ""
  extension (s: String) def combine(other: String): String = s + other


class InfixSpec extends AnyFlatSpec with should.Matchers {

  "infix" should "operator without parentheses" in {
    "2" <+> "3" <+> "4" should be ("234")
    "2" <+> ("3" <+> "4") should be ("234")
    StringMonoid.unit <+> "2" should be ("2")
  }

  it should "tick method" in {
    "2" `combine` ("3" `combine` "4") should be ("234")
    "2" `combine` StringMonoid.unit should be ("2")
  }

  it should "method with brace" in {
    "2" combine {"3" combine {"4"}} should be ("234")
    StringMonoid.unit combine {"2"}
  }
}
