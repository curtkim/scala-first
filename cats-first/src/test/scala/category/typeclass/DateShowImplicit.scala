package category.typeclass

import cats.Show
import java.util.Date
import cats.implicits.toShow

implicit val dateShow: Show[Date] = new Show[Date]{
  def show(date: Date): String =  s"${date.getTime}ms"
}

@main def main_date_implicit_show(): Unit ={
  println( new Date().show)
}
