ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

val catsVersion = "2.7.0"
val catsCore = "org.typelevel" %% "cats-core" % catsVersion
val catsFree = "org.typelevel" %% "cats-free" % catsVersion

lazy val root = (project in file("."))
  .settings(
    name := "cats-first",
    libraryDependencies ++= Seq(
      catsCore,
      catsFree,
      "org.scalacheck" %% "scalacheck" % "1.15.4" % "test",
      "org.scalatest" %% "scalatest" % "3.2.13" % "test"
    )
  )
