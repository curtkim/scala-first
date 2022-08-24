package withcats

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.data.State


class StateSpec extends AnyFlatSpec with should.Matchers {

  // S => (S, A)
  "State" should "run" in {
    val a = State[Int, String] { state => (state, s"The state is $state") }
    a.run(10).value should be((10, "The state is 10"))
    a.runS(10).value should be(10)
    a.runA(10).value should be("The state is 10")
  }

  "Composing" should "flatMap" in {
    val step1 = State[Int, String] { num =>
      val ans = num + 1
      (ans, s"Result of step1: $ans")
    }

    val step2 = State[Int, String] { num =>
      val ans = num * 2
      (ans, s"Result of step2: $ans")
    }

    val both = for {
      a <- step1
      b <- step2
    } yield (a, b)

    both.run(20).value should be((42, ("Result of step1: 21", "Result of step2: 42")))
  }

  "primitive" should "get" in {
    val getDemo = State.get[Int]
    getDemo.run(10).value should be((10, 10))
  }
  it should "set" in {
    val setDemo = State.set[Int](30)
    setDemo.run(10).value should be((30, ()))
  }
  it should "pure" in {
    val pureDemo = State.pure[Int, String]("Result")
    pureDemo.run(10).value should be(10, "Result")
  }
  it should "inspect" in {
    val inspectDemo = State.inspect[Int, String](x => s"${x}!")
    inspectDemo.run(10).value should be((10, "10!"))
  }
  it should "modify" in {
    val modifyDemo = State.modify[Int](_ + 1)
    modifyDemo.run(10).value should be((11, ()))
  }

  import State._

  "for comprehension" should "" in {
    val program: State[Int, (Int, Int, Int)] = for {
      a <- get[Int]
      _ <- set[Int](a + 1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c)

    program.run(1).value should be ((3, (1, 2, 3000)))
  }
}
