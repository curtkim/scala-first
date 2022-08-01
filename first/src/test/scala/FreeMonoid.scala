import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object FreeMonoid extends Properties(name="FreeMonoid") {

  property("free") = forAll { (c: Char) =>
    def i(x: Char) = Set(x.toString)
    def f(x: Char) = Set(x.toInt) // example

    val f_hom: PartialFunction[String, Int] = {
      case mx: String if mx.size == 1 => mx.charAt(0).toInt
    }

    def f_hom_set(smx: Set[String]) = smx map {f_hom}
    val g = f_hom_set _ compose i _
    f(c) == g(c)
  }

}
