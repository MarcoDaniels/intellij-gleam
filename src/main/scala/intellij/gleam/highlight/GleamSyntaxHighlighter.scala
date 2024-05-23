package intellij.gleam.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import intellij.gleam.gen.lang.GleamTypes
import intellij.gleam.lang.GleamLexerAdapter

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
      case GleamTypes.IMPORT | GleamTypes.PUB | GleamTypes.FN |
          GleamTypes.TYPE | GleamTypes.CASE | GleamTypes.USE | GleamTypes.LET =>
        GleamSyntaxHighlighter.KEYWORDS

      case GleamTypes.LEFT_BRACKET | GleamTypes.RIGHT_BRACKET |
          GleamTypes.LEFT_ARRAY_BRACKET | GleamTypes.RIGHT_ARRAY_BRACKET |
          GleamTypes.LEFT_PARENTHESIS | GleamTypes.RIGHT_PARENTHESIS =>
        GleamSyntaxHighlighter.BRACKETS

      case GleamTypes.LEFT_ARROW | GleamTypes.RIGHT_ARROW | GleamTypes.CONCAT |
          GleamTypes.PIPE =>
        GleamSyntaxHighlighter.OPERATION

      case GleamTypes.UPPER_CASE_TERM | GleamTypes.TUPLE =>
        GleamSyntaxHighlighter.TYPE_DEFINITION

      case GleamTypes.NUMBER_VALUE => GleamSyntaxHighlighter.NUMBER
      case GleamTypes.STRING_VALUE => GleamSyntaxHighlighter.STRING
      case GleamTypes.LINE_COMMENT => GleamSyntaxHighlighter.COMMENT
      case _                       => createTextAttributesKey("")
    })
}
