package pure

import zio.prelude.fx.ZPure


case class AccountState(balance: Int, open: Boolean)

sealed trait AccountError
case object InsufficientFunds extends AccountError

def withdraw(amount: Int) =
  for {
    state <- ZPure.get[AccountState]
    _ <- if (amount > state.balance) ZPure.fail(InsufficientFunds)
    else ZPure.set(AccountState(state.balance - amount, state.open))
  } yield ()

def decrementBalance(amount: Int) : ZPure[Nothing, AccountState, AccountState, Any, Nothing, Unit] =
  ZPure.update(state => AccountState(state.balance - amount, state.open))

val withdrawalComputation : ZPure[Nothing, Any, AccountState, Any, AccountError, Unit] =
  withdraw(10).provideState(AccountState(100, true))

val updatedAccountState : Either[AccountError, AccountState] =
  (withdrawalComputation *> ZPure.get).runEither
