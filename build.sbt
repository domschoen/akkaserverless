organization := "com.example"

scalaVersion := "2.13.6"

enablePlugins(AkkaserverlessPlugin, JavaAppPackaging, DockerPlugin)
dockerBaseImage := "docker.io/library/adoptopenjdk:11-jre-hotspot"
dockerUsername := sys.props.get("docker.username")
dockerRepository := sys.props.get("docker.registry")
ThisBuild / dynverSeparator := "-"

Compile / scalacOptions ++= Seq(
  "-target:11",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint",
    "-Ymacro-annotations")
Compile / javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-parameters" // for Jackson
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.7" % Test,
  "io.circe" %%% "circe-core"                   % "0.14.1",
  "io.circe" %%% "circe-generic"                % "0.14.1",
  "io.circe" %%% "circe-derivation"             % "0.13.0-M5",
  "io.circe" %%% "circe-derivation-annotations" % "0.13.0-M5",
  "io.circe" %%% "circe-parser"                 % "0.14.1",
  "de.heikoseeberger" %%% "akka-http-circe" % "1.38.2",
  "io.circe" %%% "circe-config" % "0.8.0"
)
