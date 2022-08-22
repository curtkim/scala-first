//https://www.faqcode4u.com/faq/422520/how-to-make-your-own-for-comprehension-compliant-scala-monad

sealed trait MaybeInt {
  def map(f: Int => Int): MaybeInt
  def flatMap(f: Int => MaybeInt): MaybeInt
}

case class SomeInt(i: Int) extends MaybeInt {
  def map(f: Int => Int): MaybeInt = {
    return SomeInt(f(i))
  }
  def flatMap(f: Int => MaybeInt): MaybeInt = {
    println(f)
    return f(i)
  }
}

case object NoInt extends MaybeInt {
  def map(f: Int => Int): MaybeInt = {
    return NoInt
  }
  def flatMap(f: Int => MaybeInt): MaybeInt = {
    println(f)
    return NoInt
  }
}

@main def my_monad_in_for(): Unit ={
  val maybe = SomeInt(1)
  val no = NoInt

  // Syntactically, the for-comprehension is transformed by the compiler into a series of flatMaps
  // followed by a final map for the yield
  /*
  val c: MaybeInt = for {
    a <- maybe
    b <- no
  } yield a+b
  println(c)
  */

  val d: MaybeInt = for {
    a <- maybe
    b <- maybe
    c <- maybe
  } yield a+b+c
  println(d)

  val m1 = SomeInt(1)
  val m2 = SomeInt(1)
  val m3 = SomeInt(1)
  val m4 = m1.flatMap(a => m2.flatMap(b => m3.map(c => a+b+c)))
  println(m4)
}
