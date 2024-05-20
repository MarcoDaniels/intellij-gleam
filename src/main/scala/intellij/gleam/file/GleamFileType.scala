package intellij.gleam.file

import com.intellij.openapi.fileTypes.LanguageFileType
import intellij.gleam.lang.GleamLanguage
import intellij.gleam.module.{gleamExtension, gleamModule, gleamModuleDescription}

import javax.swing.Icon

final class GleamFileType extends LanguageFileType(GleamLanguage) {
  override def getName: String = gleamModule
  override def getDescription: String = gleamModuleDescription
  override def getDefaultExtension: String = gleamExtension
  override def getIcon: Icon = GleamIcon.file
}
