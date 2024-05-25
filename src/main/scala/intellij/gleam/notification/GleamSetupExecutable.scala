package main.scala.intellij.gleam.notification

import com.intellij.ide.actions.ShowSettingsAction
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.{EditorNotificationPanel, EditorNotificationProvider}
import main.scala.intellij.gleam.GleamConstants.gleamExtension
import main.scala.intellij.gleam.settings.GleamSettings

import java.util.function
import javax.swing.JComponent

class GleamSetupExecutable extends EditorNotificationProvider {
  override def collectNotificationData(
      project: Project,
      file: VirtualFile
  ): function.Function[_ >: FileEditor, _ <: JComponent] = {
    val isGleam = file.getExtension == gleamExtension
    val settings = GleamSettings.getInstance(project)
    if (isGleam && settings.executable == "") { (_: FileEditor) =>
      createPanel(project)
    } else {
      null
    }
  }

  private def createPanel(project: Project) = {
    val panel = new EditorNotificationPanel(
      EditorNotificationPanel.Status.Error
    )
    panel.setText("No Gleam executable found!")
    panel.createActionLabel(
      "Configure",
      // TODO: open dialog to save in settings
      () => ShowSettingsAction.perform(project)
    )
    panel
  }
}
