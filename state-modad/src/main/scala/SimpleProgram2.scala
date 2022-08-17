object SimpleProgram2 {

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

  trait Statement {
    def execute(env: Environment): (Result, Environment)
  }
  case class Assignment(into: String, rhs: Expr) extends Statement{
    override def execute(env: Environment): (Result, Environment) = (Unit, env + (into -> Value(rhs.eval(env))))
  }
  case class Return(ret: Expr) extends Statement {
    override def execute(env: Environment): (Result, Environment) = (Integer(ret.eval(env)), env)
  }
  case class Print(expr: Expr) extends Statement {
    override def execute(env: Environment): (Result, Environment) = {
      println("Printing " + expr.eval(env))
      (Unit, env)
    }
  }

  sealed trait Result
  case class Integer(value: Int) extends Result
  case object Unit extends Result


  type Environment = Map[String, Expr]

  case class MyProgram(statements: List[Statement]) {
    def evaluate() = statements.foldLeft((List[Result](), Map[String, Expr]()))
      ((soFar, cur) => {
        val (res, newEnv) = cur.execute(soFar._2)
        (soFar._1 ++ List(res), newEnv)
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