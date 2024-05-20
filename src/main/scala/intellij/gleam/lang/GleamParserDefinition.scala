package intellij.gleam.lang

import com.intellij.lang.{ASTNode, ParserDefinition, PsiParser}
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.{FileViewProvider, PsiElement, PsiFile}
import com.intellij.psi.tree.{IFileElementType, TokenSet}
import intellij.gleam.file.GleamFile
import intellij.gleam.gen.GleamParser
import intellij.gleam.gen.lang.GleamTypes

object GleamParserDefinition {
  val FILE = new IFileElementType(GleamLanguage)
}

final class GleamParserDefinition extends ParserDefinition {

  override def createLexer(project: Project): Lexer = new GleamLexerAdapter

  override def createParser(project: Project): PsiParser = new GleamParser

  override def getFileNodeType: IFileElementType = GleamParserDefinition.FILE

  override def getCommentTokens: TokenSet =
    TokenSet.create(GleamTypes.LINE_COMMENT)

  override def getStringLiteralElements: TokenSet = TokenSet.EMPTY

  override def createElement(node: ASTNode): PsiElement =
    GleamTypes.Factory.createElement(node)

  override def createFile(viewProvider: FileViewProvider): PsiFile =
    new GleamFile(viewProvider)
}
