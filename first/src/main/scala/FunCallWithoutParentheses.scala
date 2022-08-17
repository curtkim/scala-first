class Pizza {
    // no parentheses after crustSize
    def crustSize = 12
}

@main def main(): Unit = {
  val p = Pizza()
  println(p.crustSize)
}
