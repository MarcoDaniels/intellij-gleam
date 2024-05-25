package main.scala.intellij.gleam.file

import com.intellij.openapi.util.IconLoader

import javax.swing.Icon

object GleamIcon {
  val file: Icon =
    IconLoader.getIcon("/icons/lucy.png", GleamIcon.getClass)
}
