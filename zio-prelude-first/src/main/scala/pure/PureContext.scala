package pure

import zio.prelude.fx.ZPure
import zio.prelude.fx

case class AccountEnvironment(interestRate: Double)

//val accountEnvironment: ZPure[Nothing, Unit, Unit, AccountEnvironment, Nothing, AccountEnvironment] =
//  ZPure.environment
//
//val InterestRate: ZPure[Nothing, Unit, Unit, AccountEnvironment, Nothing, Double] =

//def computeSimpleInterest(balance: Double, days: Int, interestRate: Double) =
//  ZPure.succeed(balance * days / 365 * interestRate)
//
//def accruedInterest(balance: Double, days: Int) =
//  ZPure.accessM(r => computeSimpleInterest(balance, days, r.interestRate))
//
//val interestComputation =
//  accruedInterest(100000, 30).provide(AccountEnvironment(0.05))
//
//val inteestDue : Double = interestComputation.run


@main def context_main(): Unit ={
}