package main.scala.intellij.gleam.file

import com.intellij.openapi.fileTypes.LanguageFileType
import main.scala.intellij.gleam.GleamConstants.{
  gleamExtension,
  gleamModule,
  gleamModuleDescription
}
import main.scala.intellij.gleam.lang.GleamLanguage

import javax.swing.Icon

final class GleamFileType extends LanguageFileType(GleamLanguage) {
  override def getName: String = gleamModule
  override def getDescription: String = gleamModuleDescription
  override def getDefaultExtension: String = gleamExtension
  override def getIcon: Icon = GleamIcon.file
}
