name := "bad_reflection"
organization := "org.scalamacros"
version := "2.0.0"

scalaVersion in ThisBuild := "2.13.3"

lazy val macros = (project in file("macros")).settings(
  libraryDependencies ++= Seq(scalaOrganization.value % "scala-compiler" % scalaVersion.value),
  scalacOptions ++= Seq(
   "-language:experimental.macros",
   "-language:higherKinds",
   "-language:implicitConversions"
 )
)

lazy val core = project in file("core") dependsOn macros settings (
//  scalacOptions ++= Seq(
//    "-Xprint:typer",
//    "-Ymacro-debug-lite",
//    "-Xlog-implicits",
//    "-Ytyper-debug"
//  )
)
