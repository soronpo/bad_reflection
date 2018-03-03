name := "bad_reflection"
organization := "org.scalamacros"
version := "2.0.0"

//scalaVersion in ThisBuild := "2.12.4"
//scalaVersion in ThisBuild := "2.13.0-M2"
scalaVersion in ThisBuild := "2.13.0-M3"

lazy val macros = (project in file("macros")).settings(
 libraryDependencies ++= Seq(
   "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "com.chuusai" %% "shapeless" % "2.3.3"
 ),
 scalacOptions ++= Seq(
   "-language:experimental.macros",
   "-language:higherKinds",
   "-language:implicitConversions"
 )
)

lazy val core = project in file("core") dependsOn macros settings (
  scalacOptions ++= Seq(
    "-Xprint:typer",
    "-Ymacro-debug-lite",
    "-Xlog-implicits",
    "-Ytyper-debug"
  )
)
