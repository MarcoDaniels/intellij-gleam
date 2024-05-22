package intellij.gleam.highlight

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.{
  AttributesDescriptor,
  ColorDescriptor,
  ColorSettingsPage
}
import intellij.gleam.file.GleamIcon
import intellij.gleam.module.gleamName

import java.util
import javax.swing.Icon
import scala.jdk.CollectionConverters.MapHasAsJava

private object GleamColorSettingsPage {
  private val DESCRIPTORS = Array[AttributesDescriptor](
    new AttributesDescriptor("Keyword", GleamSyntaxHighlighter.KEYWORDS),
    new AttributesDescriptor("Types", GleamSyntaxHighlighter.TYPE_DEFINITION),
    new AttributesDescriptor("Operation", GleamSyntaxHighlighter.OPERATION),
    new AttributesDescriptor("String", GleamSyntaxHighlighter.STRING),
    new AttributesDescriptor("Number", GleamSyntaxHighlighter.NUMBER),
    new AttributesDescriptor("Comment", GleamSyntaxHighlighter.COMMENT),
    new AttributesDescriptor("Brackets", GleamSyntaxHighlighter.BRACKETS)
  )
}

class GleamColorSettingsPage extends ColorSettingsPage {
  override def getIcon: Icon = GleamIcon.file

  override def getHighlighter: SyntaxHighlighter = new GleamSyntaxHighlighter()

  override def getDemoText: String =
    s"""
       |import gleam/io
       |
       |// main function
       |pub fn main() {
       |  io.println("hello" <> "friend!")
       |  io.println(200)
       |}
       |
       |type User {
       |  User(name: String)
       |}
       |""".stripMargin

  override def getAdditionalHighlightingTagToDescriptorMap
      : util.Map[String, TextAttributesKey] =
    Map("empty" -> DefaultLanguageHighlighterColors.IDENTIFIER).asJava

  override def getAttributeDescriptors: Array[AttributesDescriptor] =
    GleamColorSettingsPage.DESCRIPTORS

  override def getColorDescriptors: Array[ColorDescriptor] =
    ColorDescriptor.EMPTY_ARRAY

  override def getDisplayName: String = gleamName
}
