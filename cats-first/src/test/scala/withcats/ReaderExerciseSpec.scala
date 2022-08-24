package withcats

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import cats.data.Reader
import cats.syntax.applicative._ // for pure

class ReaderExerciseSpec extends AnyFlatSpec with should.Matchers {
  final case class Db(
    usernames: Map[Int, String],
    passwords: Map[String, String]
  )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      ok <- username.map{ username => checkPassword(username, password)}.getOrElse{false.pure[DbReader]}
    } yield ok


  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)

  "checkLogin" should "success" in {
    checkLogin(1, "zerocool").run(db) should be (true)
  }

  it should "fail when id not exist" in {
    checkLogin(4, "davinci").run(db) should be (false)
    checkLogin(3, "davinci").run(db) should be (false)
  }
}
