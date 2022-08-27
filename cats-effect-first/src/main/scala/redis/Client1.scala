package redis

import cats.effect.{IO, Resource}
import dev.profunktor.redis4cats._
import dev.profunktor.redis4cats.algebra.StringCommands
import dev.profunktor.redis4cats.connection._
import dev.profunktor.redis4cats.data.RedisCodec
import dev.profunktor.redis4cats.log4cats._
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger


object Client1 {
  implicit val logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  val stringCodec: RedisCodec[String, String] = RedisCodec.Utf8

  val commandsApi: Resource[IO, StringCommands[IO, String, String]] =
    RedisClient[IO]
     .from("redis://localhost")
     .flatMap(Redis[IO].fromClient(_, stringCodec))



}
