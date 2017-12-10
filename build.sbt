name := "bad_reflection"
organization := "org.scalamacros"
version := "2.0.0"

scalaVersion in ThisBuild := "2.12.4"
run <<= run in Compile in core

lazy val macros = (project in file("macros")).settings(
 libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
 scalacOptions ++= Seq(
   "-language:experimental.macros",
   "-language:higherKinds",
   "-language:implicitConversions"
 )
)


lazy val core = (project in file("core")) dependsOn macros
