
@main def partial_function_main(): Unit ={

  val squareRoot: PartialFunction[Double, Double] = {
    case x if x>= 0 => Math.sqrt(x)
    //def apply(x: Double) = Math.sqrt(x)
    //def isDefinedAt(x: Double) = x >= 0
  }

  println(squareRoot(9))
  //println(squareRoot(-4))


  val parseRange: PartialFunction[Int, Int] = {
    case x: Int if x> 10 => x+1
  }

  println( List(15, 3).collect {parseRange})

  println( List(15).map {parseRange})
  try {
    println(List(15, 3).map {parseRange})
  }
  catch{
    case e: MatchError => println(s"message : ${e.getMessage()} ")
  }

}