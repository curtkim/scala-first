package ch08_type

trait Item{def productNumber: String}
trait Buyable extends Item {def price: Int}
trait Book extends Buyable {def isbn: String}

trait Pipeline[T]:
  def process(t: T): T

trait Producer[+T]:
  def make: T

trait Consumer[-T]:
  def take(t: T): Unit


def oneOf(
         p1: Pipeline[Buyable],
         p2: Pipeline[Buyable],
         b: Buyable
         ) : Buyable =
  var b1 = p1.process(b)
  var b2 = p2.process(b)
  if b1.price < b2.price then b1 else b2

