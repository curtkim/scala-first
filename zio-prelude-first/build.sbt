ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "zio-prelude-first",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.0",
      "dev.zio" %% "zio-prelude" % "1.0.0-RC15",
      "org.typelevel" %% "cats-effect" % "3.3.14",
      "org.scalatest" %% "scalatest" % "3.2.13" % "test"
    )
  )
