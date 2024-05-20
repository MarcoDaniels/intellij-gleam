package intellij.gleam.lang

import com.intellij.lexer.FlexAdapter

import java.io.Reader

class GleamLexerAdapter
    extends FlexAdapter(new _GleamLexer(null.asInstanceOf[Reader])) {}
