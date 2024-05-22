package intellij.gleam.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import com.intellij.platform.lsp.api.customization.{
  LspCommandsSupport,
  LspCompletionSupport
}
import intellij.gleam.module.{gleamExtension, gleamName}

class GleamLSPServerDescriptor(project: Project, executable: String)
    extends ProjectWideLspServerDescriptor(project, gleamName) {
  override def createCommandLine(): GeneralCommandLine =
    new GeneralCommandLine(executable, "lsp")

  override def isSupportedFile(virtualFile: VirtualFile): Boolean =
    virtualFile.getExtension == gleamExtension

  override def getLspGoToDefinitionSupport(): Boolean = true

  override def getLspCompletionSupport: LspCompletionSupport =
    super.getLspCompletionSupport

  override def getLspHoverSupport(): Boolean = true
}
