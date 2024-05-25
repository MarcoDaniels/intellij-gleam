package main.scala.intellij.gleam.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.{RawCommandLineEditor, TitledSeparator}
import com.intellij.util.ui.FormBuilder

import javax.swing.{JComponent, JPanel}

class GleamSettingsConfigurable(project: Project) extends Configurable {

  private val executable: RawCommandLineEditor = new RawCommandLineEditor()
  private val enableLSP: JBCheckBox = new JBCheckBox("Enable LSP")

  override def getDisplayName: String = "Gleam Language"

  override def createComponent(): JComponent = {
    // TODO: create popup in search for executable
    executable.getEditorField.getEmptyText
      .setText("Path for Gleam executable")
    executable.getEditorField.getAccessibleContext
      .setAccessibleName("Path for Gleam executable")

    FormBuilder
      .createFormBuilder()
      .addComponent(new TitledSeparator("Path For Gleam Executable"))
      .addLabeledComponent("Executable:", executable)
      .addComponent(new TitledSeparator("Language Server Configuration"))
      .addComponent(enableLSP)
      .addComponentFillVertically(new JPanel(), 0)
      .getPanel
  }

  override def isModified: Boolean = {
    val settings = GleamSettings.getInstance(project)

    Configurable
      .isFieldModified(executable.getTextField, settings.executable) ||
    Configurable.isCheckboxModified(
      enableLSP,
      settings.enableLSP
    )
  }

  override def apply(): Unit = {
    val settings = GleamSettings.getInstance(project)

    settings.setExecutable(executable.getText)
    settings.setEnableLSP(enableLSP.isSelected)
  }

  override def reset(): Unit = {
    val settings = GleamSettings.getInstance(project)

    executable.setText(settings.executable)
    enableLSP.setSelected(settings.enableLSP)
  }

}
