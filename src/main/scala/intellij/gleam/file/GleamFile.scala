package main.scala.intellij.gleam.file

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import main.scala.intellij.gleam.GleamConstants.gleamModule
import main.scala.intellij.gleam.lang.GleamLanguage

import javax.swing.Icon

class GleamFile(val viewProvider: FileViewProvider)
    extends PsiFileBase(viewProvider, GleamLanguage) {

  override def getFileType: GleamFileType = new GleamFileType

  override def toString: String = gleamModule

  override def getIcon(flags: Int): Icon = super.getIcon(flags)
}
