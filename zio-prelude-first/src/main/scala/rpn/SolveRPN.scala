package rpn

object SolveRPN extends App {

  val equation ="10 4 3 + 2 * -"

  println(solveRPN(equation))

  def solveRPN(eqn: String): Double = {
    val items = eqn.split(" ")
    val accmulator = List[Double]()
    items.foldLeft(accmulator)(foldingFunction).head
  }

  def foldingFunction(stack: List[Double], a: String): List[Double] = stack match {
    case List() => a.toDouble :: stack
    case List(_) => a.toDouble :: stack
    case x::y::ys => a match {
      case "*" => x * y :: ys
      case "+" => x + y :: ys
      case "-" => y - x :: ys
      case "/" => y / x :: ys
      case s:String => s.toDouble :: stack
    }
  }
}
