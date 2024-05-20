package intellij.gleam.actions

import com.intellij.ide.actions.{CreateFileFromTemplateAction, CreateFileFromTemplateDialog}
import com.intellij.openapi.project.{DumbAware, Project}
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiDirectory
import intellij.gleam.file.GleamIcon
import intellij.gleam.module.gleamModule

class CreateGleamModule
    extends CreateFileFromTemplateAction(gleamModule, "", GleamIcon.file)
    with DumbAware {
  override def getActionName(
      directory: PsiDirectory,
      newName: String,
      templateName: String
  ): String =
    gleamModule

  override def buildDialog(
      project: Project,
      directory: PsiDirectory,
      builder: CreateFileFromTemplateDialog.Builder
  ): Unit =
    builder
      .setTitle(gleamModule)
      .addKind("Gleam file", GleamIcon.file, gleamModule)
      .setValidator(new InputValidatorEx {
        override def checkInput(inputString: String): Boolean =
          true

        override def getErrorText(inputString: String): String =
          validateInput(inputString).orNull

        override def canClose(inputString: String): Boolean =
          validateInput(inputString) match {
            case Some(_) => false
            case None    => !StringUtil.isEmptyOrSpaces(inputString)
          }

        def validateInput(inputString: String): Option[String] =
          inputString match {
            case i if i.contains(" ") =>
              // TODO: other validations
              Some("Gleam file name cannot contain spaces")
            case _ => None
          }
      })
  // TODO: what about when file is created with directory \
}
