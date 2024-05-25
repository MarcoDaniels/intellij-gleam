package main.scala.intellij.gleam.lang

import com.intellij.lexer.FlexAdapter
import main.java.intellij.gleam._GleamLexer

import java.io.Reader

class GleamLexerAdapter
    extends FlexAdapter(new _GleamLexer(null.asInstanceOf[Reader])) {}
