name := "Aquarium Simulator"

ThisBuild / scalaVersion := "3.1.3"

libraryDependencies ++= Seq(
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

fork := true
