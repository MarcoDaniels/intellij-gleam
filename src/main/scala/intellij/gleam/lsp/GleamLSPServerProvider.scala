package main.scala.intellij.gleam.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import main.scala.intellij.gleam.Gleam

final class GleamLSPServerProvider extends LspServerSupportProvider {
  override def fileOpened(
      project: Project,
      virtualFile: VirtualFile,
      lspServerStarter: LspServerSupportProvider.LspServerStarter
  ): Unit = {
    Gleam(project, virtualFile) match {
      case Some(gleam) =>
        gleam.executable match {
          case Some(executable) =>
            lspServerStarter.ensureServerStarted(
              new GleamLSPServerDescriptor(
                gleam.project,
                executable
              )
            )
          case None => ()
        }
      case None => ()
    }

  }
}
