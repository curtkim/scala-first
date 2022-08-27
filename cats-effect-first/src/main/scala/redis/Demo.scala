package redis

import cats.effect.IO
import dev.profunktor.redis4cats.codecs.Codecs
import dev.profunktor.redis4cats.codecs.splits._
import dev.profunktor.redis4cats.data.RedisCodec

object Demo {
  val redisURI: String                        = "redis://localhost"
  val redisClusterURI: String                 = "redis://localhost:30001"
  val stringCodec: RedisCodec[String, String] = RedisCodec.Utf8
  val longCodec: RedisCodec[String, Long]     = Codecs.derive(stringCodec, stringLongEpi)

  def putStrLn[A](a: A): IO[Unit] = IO.println(a)
}
