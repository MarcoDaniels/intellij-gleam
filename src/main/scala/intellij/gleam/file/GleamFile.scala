package intellij.gleam.file

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import intellij.gleam.lang.GleamLanguage
import intellij.gleam.module.gleamModule

import javax.swing.Icon

class GleamFile(val viewProvider: FileViewProvider)
    extends PsiFileBase(viewProvider, GleamLanguage) {

  override def getFileType: GleamFileType = new GleamFileType

  override def toString: String = gleamModule

  override def getIcon(flags: Int): Icon = super.getIcon(flags)
}
