package main.scala.intellij.gleam.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import main.scala.intellij.gleam.lang.GleamLexerAdapter
import main.java.intellij.gleam.GleamTypes._

object GleamSyntaxHighlighter {
  val KEYWORDS: TextAttributesKey = createTextAttributesKey(
    "GLEAM_KEYWORDS",
    DefaultLanguageHighlighterColors.KEYWORD
  )

  val TYPE_DEFINITION: TextAttributesKey = createTextAttributesKey(
    "GLEAM_TYPE_DEFINITION",
    DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
  )

  val BRACKETS: TextAttributesKey = createTextAttributesKey(
    "GLEAM_BRACKETS",
    DefaultLanguageHighlighterColors.BRACKETS
  )

  val STRING: TextAttributesKey = createTextAttributesKey(
    "GLEAM_STRING",
    DefaultLanguageHighlighterColors.STRING
  )

  val NUMBER: TextAttributesKey = createTextAttributesKey(
    "GLEAM_NUMBER",
    DefaultLanguageHighlighterColors.NUMBER
  )

  val OPERATION: TextAttributesKey = createTextAttributesKey(
    "GLEAM_OPERATION",
    DefaultLanguageHighlighterColors.NUMBER
  )

  val COMMENT: TextAttributesKey = createTextAttributesKey(
    "GLEAM_COMMENT",
    DefaultLanguageHighlighterColors.LINE_COMMENT
  )
}

class GleamSyntaxHighlighter extends SyntaxHighlighterBase {

  override def getHighlightingLexer: Lexer = new GleamLexerAdapter()

  override def getTokenHighlights(
      tokenType: IElementType
  ): Array[TextAttributesKey] =
    Array(tokenType match {
      case IMPORT | PUB | FN | TYPE | CASE | USE | LET =>
        GleamSyntaxHighlighter.KEYWORDS

      case LEFT_BRACKET | RIGHT_BRACKET | LEFT_ARRAY_BRACKET |
          RIGHT_ARRAY_BRACKET | LEFT_PARENTHESIS | RIGHT_PARENTHESIS =>
        GleamSyntaxHighlighter.BRACKETS

      case LEFT_ARROW | RIGHT_ARROW | CONCAT | PIPE =>
        GleamSyntaxHighlighter.OPERATION

      case UPPER_CASE_TERM | TUPLE =>
        GleamSyntaxHighlighter.TYPE_DEFINITION

      case NUMBER_VALUE => GleamSyntaxHighlighter.NUMBER
      case STRING_VALUE => GleamSyntaxHighlighter.STRING
      case LINE_COMMENT => GleamSyntaxHighlighter.COMMENT
      case _            => createTextAttributesKey("")
    })
}
