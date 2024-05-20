package intellij.gleam.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import intellij.gleam.module.gleamExtension
import intellij.gleam.settings.GleamSettings

final class GleamLSPServerProvider extends LspServerSupportProvider {
  override def fileOpened(
      project: Project,
      virtualFile: VirtualFile,
      lspServerStarter: LspServerSupportProvider.LspServerStarter
  ): Unit = {
    val settings = GleamSettings.getInstance(project)
    if (
      virtualFile.getExtension == gleamExtension && settings.enableLSP && settings.executable != ""
    ) {
      lspServerStarter.ensureServerStarted(
        new GleamLSPServerDescriptor(project, settings.executable)
      )
    }
  }
}
