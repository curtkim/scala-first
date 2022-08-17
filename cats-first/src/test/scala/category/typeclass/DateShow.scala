package category.typeclass

import cats.Show
import java.util.Date

@main def main_date_show(): Unit ={
  given DateShow : Show[Date] with
    def show(date: Date): String = s"${date.getTime}ms"

  println(Show[Date].show(new Date()))
}
