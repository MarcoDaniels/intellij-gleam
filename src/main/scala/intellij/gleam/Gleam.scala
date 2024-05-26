package main.scala.intellij.gleam

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import main.scala.intellij.gleam.GleamConstants.gleamExtension
import main.scala.intellij.gleam.settings.GleamSettings

case class Gleam(
    project: Project,
    lsp: Boolean,
    executable: Option[String]
)

object Gleam {
  def apply(
      project: Project,
      virtualFile: VirtualFile
  ): Option[Gleam] = {
    val isGleamFile = virtualFile.getExtension == gleamExtension
    val settings = GleamSettings.getInstance(project)
    if (isGleamFile) {
      Some(
        new Gleam(
          project,
          settings.enableLSP,
          if (settings.executable.trim.nonEmpty) {
            Some(settings.executable)
          } else {
            None
          }
        )
      )
    } else {
      None
    }
  }
}
