package pure

import zio.Chunk
import zio.prelude.fx.ZPure


def withdrawLog(amount: Int) : ZPure[String, AccountState, AccountState, Any, AccountError, Unit] =
  ZPure.log("Attempting to withdraw") *> withdraw(amount) <* ZPure.log(s"Withdrew $amount")


val withdrawalComputationLog: ZPure[String, AccountState, AccountState, Any, AccountError, Unit] =
  withdrawLog(10)

val log: Chunk[String] =
  withdrawalComputationLog.runAll(AccountState(100, true))._1