object SimpleProgram3 {

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

  type StateAction[S, A] = S => (A, S)
  type Environment = Map[String, Expr]

  case class State[S, A](run: S => (A,S)) {
    def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {
      val (a, s2) = this.run(s)
      f(a).run(s2)
    })
    def unit[A, S](a: A): State[S, A] = State(s => (a, s))
    def map[B](f: A => B): State[S, B] = flatMap(a => unit(f(a)))
  }

  trait Statement {
    def build(): State[Environment, Result]
  }
//  case class Assignment(into: String, rhs: Expr) extends Statement{
//    override def build(): StateAction[Environment, Result] = (env) => (Unit, env + (into -> Value(rhs.eval(env))))
//  }
//  case class Return(ret: Expr) extends Statement {
//    override def build(): StateAction[Environment, Result] = (env) => (Integer(ret.eval(env)), env)
//  }
//  case class Print(expr: Expr) extends Statement {
//    override def build(): StateAction[Environment, Result] = (env) => {
//      println("Printing " + expr.eval(env))
//      (Unit, env)
//    }
//  }

  sealed trait Result
  case class Integer(value: Int) extends Result
  case object Unit extends Result

  def sequence[A, S](list: List[State[S, A]]): State[S, List[A]] = State(s =>
    list match {
      case Nil => (List.empty, s)
      case h :: t =>
        h.flatMap(head => sequence(t).map(tail => head :: tail)).run(s)
    })

//  case class Program(statements: List[Statement]) {
//    private def build = for {
//      responses <- sequence(statements.map(_.build))
//    } yield responses.last //We generally return the last evaluated value.
//
//    def evaluate = build.run(Map[String, Expr]())
//  }

//  def main(args: Array[String]): Unit = {
//    val program = Program(List(
//      Assignment("a", Add(Value(1), Value(1))),
//      Return(Add(Variable("a"), Value(1))),
//      Print(Add(Variable("a"), Value(1))),
//    ))
//    program.evaluate
//  }
}