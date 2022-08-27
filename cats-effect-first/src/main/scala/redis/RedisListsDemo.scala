package redis

import cats.effect.{ IO, Resource }
import dev.profunktor.redis4cats.Redis
import dev.profunktor.redis4cats.algebra.ListCommands
import dev.profunktor.redis4cats.effect.Log.NoOp._


object RedisListsDemo extends LoggerIOApp {

  import Demo._

  val program: IO[Unit] = {
    val testKey = "listos"

    val commandsApi: Resource[IO, ListCommands[IO, String, String]] = Redis[IO].utf8(redisURI)

    commandsApi
      .use { cmd =>
        for {
          d <- cmd.rPush(testKey, "one", "two", "three")
          _ <- putStrLn(s"Length on Push: $d")
          x <- cmd.lRange(testKey, 0, 10)
          _ <- putStrLn(s"Range: $x")
          y <- cmd.lLen(testKey)
          _ <- putStrLn(s"Length: $y")
          a <- cmd.lPop(testKey)
          _ <- putStrLn(s"Left Pop: $a")
          b <- cmd.rPop(testKey)
          _ <- putStrLn(s"Right Pop: $b")
          z <- cmd.lRange(testKey, 0, 10)
          _ <- putStrLn(s"Range: $z")
          c <- cmd.lInsertAfter(testKey, "two", "four")
          _ <- putStrLn(s"Length on Insert After: $c")
          e <- cmd.lInsertBefore(testKey, "four", "three")
          _ <- putStrLn(s"Length on Insert Before: $e")
          f <- cmd.lRange(testKey, 0, 10)
          _ <- putStrLn(s"Range: $f")
          _ <- cmd.lSet(testKey, 0, "four")
          g <- cmd.lRange(testKey, 0, 10)
          _ <- putStrLn(s"Range after Set: $g")
          h <- cmd.lRem(testKey, 2, "four")
          _ <- putStrLn(s"Removed: $h")
          i <- cmd.lRange(testKey, 0, 10)
          _ <- putStrLn(s"Range: $i")
        } yield ()
      }
  }

}

