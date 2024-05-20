package intellij.gleam.lang

import com.intellij.psi.tree.IElementType

class GleamTokenType(val debugName: String)
    extends IElementType(debugName, GleamLanguage) {
  override def toString: String = "GleamTokenType." + super.toString
}
