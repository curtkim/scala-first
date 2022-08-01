val sum: (Int, Int) => Int = (x,y) => x+y
val curriedSum: Int => Int => Int = sum.curried

def sum2(x: Int, y:Int): Int = x+y
val curriedSum2: Int => Int => Int = (sum2 _).curried

val increment: Int => Int = curriedSum(1)

// multiple argument list
def curriedSum3(x: Int)(y: Int): Int = x+y
val curriedSum4: Int => Int => Int = curriedSum3
val increment3: Int => Int = curriedSum3(1)


@main def curried_main(): Unit = {
  assert(curriedSum(1)(2) == 3)
  assert(curriedSum2(1)(2) == 3)
  assert(increment(2) == 3)

  assert(increment3(2) == 3)
  assert(curriedSum3(1)(2) == 3)
}