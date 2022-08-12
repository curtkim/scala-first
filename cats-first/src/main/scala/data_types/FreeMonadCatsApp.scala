package data_types

import cats.data.EitherK
import cats.free.Free
import cats.{Id, InjectK, ~>}
import scala.collection.mutable.ListBuffer

import scala.io.StdIn.readLine

/* Handles user interaction */
sealed trait Interact[A]
case class Ask(prompt: String) extends Interact[String]
case class Tell(msg: String) extends Interact[Unit]

/* Represents persistence operations */
sealed trait DataOp[A]
case class AddCat(a: String) extends DataOp[Unit]
case class GetAllCats() extends DataOp[List[String]]

type CatsApp[A] = EitherK[DataOp, Interact, A]

class Interacts[F[_]](implicit I: InjectK[Interact, F]) {
  def tell(msg: String): Free[F, Unit] = Free.liftInject[F](Tell(msg))
  def ask(prompt: String): Free[F, String] = Free.liftInject[F](Ask(prompt))
}

object Interacts {
  implicit def interacts[F[_]](implicit I: InjectK[Interact, F]): Interacts[F] = new Interacts[F]
}

class DataSource[F[_]](implicit I: InjectK[DataOp, F]) {
  def addCat(a: String): Free[F, Unit] = Free.liftInject[F](AddCat(a))
  def getAllCats: Free[F, List[String]] = Free.liftInject[F](GetAllCats())
}

object DataSource {
  implicit def dataSource[F[_]](implicit I: InjectK[DataOp, F]): DataSource[F] = new DataSource[F]
}


def program(implicit I: Interacts[CatsApp], D: DataSource[CatsApp]) = {
  import I._, D._
  for {
    cat <- ask("What's the kitty's name?")
    _ <- addCat(cat)
    cats <- getAllCats
    _ <- tell(cats.toString)
  } yield ()
}

object ConsoleCatsInterpreter extends (Interact ~> Id) {
  def apply[A](i: Interact[A]) = i match {
    case Ask(prompt) =>
      println(prompt)
      readLine()
    case Tell(msg) =>
      println(msg)
  }
}

object InMemoryDatasourceInterpreter extends (DataOp ~> Id) {
  private[this] val memDataSet = new ListBuffer[String]
  def apply[A](fa: DataOp[A]) = fa match {
    case AddCat(a) => memDataSet.append(a); ()
    case GetAllCats() => memDataSet.toList
  }
}

val interpreter = InMemoryDatasourceInterpreter or ConsoleCatsInterpreter

import DataSource._, Interacts._

@main def cats_app_main(): Unit ={
  program.foldMap(interpreter)
}
