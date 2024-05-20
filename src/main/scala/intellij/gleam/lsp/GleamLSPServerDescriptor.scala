package intellij.gleam.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import intellij.gleam.module.{gleamExtension, gleamName}

class GleamLSPServerDescriptor(project: Project, executable: String)
    extends ProjectWideLspServerDescriptor(project, gleamName) {
  override def createCommandLine(): GeneralCommandLine =
    new GeneralCommandLine(executable, "lsp")

  override def isSupportedFile(virtualFile: VirtualFile): Boolean =
    virtualFile.getExtension == gleamExtension
}
