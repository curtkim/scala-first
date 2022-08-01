package ch09_contextual_abstraction

case class Config(port: Int, baseUrl: String)

def renderWebSite(path: String)(using Config): String =
  "<html>" + renderWidget(List("cart")) + "<html>"

def renderWidget(items : List[String])(using c: Config): String =
  ""

val config = Config(8080, "docs.scala-lang.org")

def main(): Unit ={
  renderWebSite("/home")(using config)
}

def main2(): Unit = {
  given Config = config
  renderWebSite("/home")
}