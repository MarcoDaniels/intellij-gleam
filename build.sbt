import org.jetbrains.sbtidea.Keys._

lazy val gleam =
  project
    .in(file("."))
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      version := "0.0.1-SNAPSHOT",
      scalaVersion := "2.13.10",
      ThisBuild / intellijPluginName := "gleam",
      ThisBuild / intellijBuild := "233.14015.106",
      ThisBuild / intellijPlatform := IntelliJPlatform.IdeaUltimate,
      Global / intellijAttachSources := true,
      Compile / javacOptions ++= "--release" :: "17" :: Nil,
      intellijPlugins += "com.intellij.properties".toPlugin,
      libraryDependencies += "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.5" withSources (),
      Compile / unmanagedResourceDirectories += baseDirectory.value / "resources",
      Compile / unmanagedSourceDirectories += baseDirectory.value / "gen",
      Test / unmanagedResourceDirectories += baseDirectory.value / "testResources"
    )
