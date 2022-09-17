name := "Aquarium Simulator"

ThisBuild / scalaVersion := "3.1.3"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "18.0.1-R28",
  "org.scalatest" %% "scalatest" % "3.2.13" % Test
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8",
  "-feature",
  "-Werror",
  "-explain",
  //"-new-syntax", "-rewrite",
  "-indent", "-rewrite"
)

Compile / compile := (Compile/compile dependsOn Compile/scalafmt).value
Test / compile := (Test/compile dependsOn Test/scalafmt).value

fork := true
