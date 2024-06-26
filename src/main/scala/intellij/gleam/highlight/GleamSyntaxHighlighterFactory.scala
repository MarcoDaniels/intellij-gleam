package main.scala.intellij.gleam.highlight

import com.intellij.openapi.fileTypes.{
  SyntaxHighlighter,
  SyntaxHighlighterFactory
}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

final class GleamSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
  override def getSyntaxHighlighter(
      project: Project,
      virtualFile: VirtualFile
  ): SyntaxHighlighter = new GleamSyntaxHighlighter()
}
