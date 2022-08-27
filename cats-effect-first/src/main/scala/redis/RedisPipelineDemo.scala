package redis

import cats.effect._
import cats.syntax.all._

import dev.profunktor.redis4cats.Redis
import dev.profunktor.redis4cats.RedisCommands
import dev.profunktor.redis4cats.effect.Log.NoOp._
import dev.profunktor.redis4cats.tx._

object RedisPipelineDemo extends LoggerIOApp {

  import Demo._

  val program: IO[Unit] = {
    val key1 = "testp1"
    val key2 = "testp2"

    val showResult: String => Option[String] => IO[Unit] = key =>
      _.fold(putStrLn(s"Not found key: $key"))(s => putStrLn(s"$key: $s"))

    val commandsApi: Resource[IO, RedisCommands[IO, String, String]] = Redis[IO].utf8(redisURI)

    commandsApi.use { redis =>
      val getters = redis.get(key1).flatTap(showResult(key1))
        *> redis.get(key2).flatTap(showResult(key2))

      val ops = (store: TxStore[IO, String, Option[String]]) =>
        List(
          redis.set(key1, "noop"),
          redis.set(key2, "windows"),
          redis.get(key1).flatMap(store.set(s"$key1-v1")),
          redis.set(key1, "nix"),
          redis.set(key2, "linux"),
          redis.get(key1).flatMap(store.set(s"$key1-v2"))
        )

      val prog =
        redis
          .pipeline(ops)
          .flatMap(kv => IO.println(s"KV: $kv"))
          .recoverWith {
            case e =>
              putStrLn(s"[Error] - ${e.getMessage}")
          }

      getters >> prog >> getters >> putStrLn("keep doing stuff...")
    }
  }

}

