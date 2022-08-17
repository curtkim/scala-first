object SimpleProgram {

  sealed trait Expr {
    def eval(env: Environment): Int
  }
  case class Value(value: Int) extends Expr {
    override def eval(env: Environment): Int = value
  }
  case class Variable(name: String) extends Expr {
    override def eval(env: Environment): Int = env(name).eval(env)
  }
  case class Add(lhs: Expr, rhs: Expr) extends Expr {
    override def eval(env: Environment): Int = lhs.eval(env) + rhs.eval(env)
  }

  trait Statement
  case class Assignment(into: String, rhs: Expr) extends Statement
  case class Return(ret: Expr) extends Statement
  case class Print(expr: Expr) extends Statement

  sealed trait Result
  case class Integer(value: Int) extends Result
  case object Void extends Result


  type Environment = Map[String, Expr]

  class MyProgram(statements: List[Statement]) {
    var state: Environment = Map.empty

    def evaluate(): List[Result] =
      statements.map(st => st match {
        case assign: Assignment => {
          state = state + (assign.into -> Value(assign.rhs.eval(state)))
          Void
        }
        case returnSt: Return => Integer(returnSt.ret.eval(state))
        case print: Print => {
          println("Printing " + print.expr.eval(state))
          Void
        }
        case _ => Void
      })
  }


  def main(args: Array[String]): Unit = {
    val program = MyProgram(List(
      Assignment("a", Add(Value(1), Value(2))),
      Print(Add(Variable("a"), Value(1))),
      Assignment("b", Variable("a")),
      Return(Add(Variable("b"), Value(2)))
    ))

    program.evaluate()
  }
}