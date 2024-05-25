import org.jetbrains.sbtidea.Keys._

ThisBuild / scalaVersion := "2.13.10"
ThisBuild / intellijPluginName := "gleam"
ThisBuild / intellijBuild := "233.14015.106"
// LSP requires ultimate version
ThisBuild / intellijPlatform := IntelliJPlatform.IdeaUltimate

lazy val gleam =
  project
    .in(file("."))
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      version := "0.0.1",
      Compile / scalaSource := baseDirectory.value / "src",
      Compile / unmanagedSourceDirectories += baseDirectory.value / "gen",
      Compile / resourceDirectory := baseDirectory.value / "resources",
      Global / javacOptions ++= Seq("-source", "17", "-target", "17"),
      Global / scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked"),
      intellijPlugins += "com.intellij.properties".toPlugin,
      packageLibraryMappings := Seq.empty,
      patchPluginXml := pluginXmlOptions { xml =>
        xml.version = version.value
      }
    )
