package main.scala.intellij.gleam.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import main.scala.intellij.gleam.Gleam
import main.scala.intellij.gleam.GleamConstants.gleamName

class GleamLSPServerDescriptor(project: Project, executable: String)
    extends ProjectWideLspServerDescriptor(project, gleamName) {
  override def createCommandLine(): GeneralCommandLine =
    new GeneralCommandLine(executable, "lsp")

  override def isSupportedFile(virtualFile: VirtualFile): Boolean =
    Gleam(project, virtualFile) match {
      case Some(_) => true
      case None    => false
    }

  override def getLspGoToDefinitionSupport(): Boolean = true

  override def getLspCompletionSupport: LspCompletionSupport =
    super.getLspCompletionSupport

  override def getLspHoverSupport(): Boolean = true
}
