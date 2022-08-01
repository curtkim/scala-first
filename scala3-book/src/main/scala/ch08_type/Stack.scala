package ch08_type

class Stack[A]:
  private var elements: List[A] = Nil

  def push(x: A): Unit = {elements = elements.prepended(x)}
  def peek: A = elements.head
  def pop(): A =
    val currentTop = peek
    elements = elements.tail
    currentTop

@main def stack_main(): Unit ={
  val stack = Stack[Int]
  stack.push(1)
  stack.push(2)
  println(stack.pop())
  println(stack.pop())
}
