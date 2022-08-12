ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

val catsVersion = "2.7.0"
val catsCore = "org.typelevel" %% "cats-core" % catsVersion
val catsFree = "org.typelevel" %% "cats-free" % catsVersion
val catsEffect = "org.typelevel" %% "cats-effect" % "3.3.14"


val Http4sVersion = "0.23.14"
val circeVersion = "0.14.1"

lazy val root = (project in file("."))
  .settings(
    name := "cats-first",
    libraryDependencies ++= Seq(
      catsCore,
      catsFree,
      catsEffect,

      "org.http4s" %% "http4s-ember-client" % Http4sVersion,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "org.scalacheck" %% "scalacheck" % "1.15.4" % "test",
      "org.scalatest" %% "scalatest" % "3.2.13" % "test"
    )
  )
