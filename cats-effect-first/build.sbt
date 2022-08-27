ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "cats-effect-first",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.3.14",
      "org.typelevel" %% "log4cats-slf4j" % "2.4.0",
      "dev.profunktor" %% "redis4cats-effects" % "1.2.0",
      "dev.profunktor" %% "redis4cats-streams" % "1.2.0",
      "dev.profunktor" %% "redis4cats-log4cats" % "1.2.0",
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % "test"
    ),
  )
