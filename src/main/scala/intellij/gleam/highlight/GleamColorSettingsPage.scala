package main.scala.intellij.gleam.highlight

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.{
  AttributesDescriptor,
  ColorDescriptor,
  ColorSettingsPage
}
import main.scala.intellij.gleam.GleamConstants.gleamName
import main.scala.intellij.gleam.file.GleamIcon

import java.util
import javax.swing.Icon
import scala.jdk.CollectionConverters.MapHasAsJava

private object GleamColorSettingsPage {
  private val DESCRIPTORS = Array[AttributesDescriptor](
    new AttributesDescriptor("Keyword", GleamSyntaxHighlighter.KEYWORDS),
    new AttributesDescriptor("Types", GleamSyntaxHighlighter.TYPE_DEFINITION),
    new AttributesDescriptor("Brackets", GleamSyntaxHighlighter.BRACKETS),
    new AttributesDescriptor("String", GleamSyntaxHighlighter.STRING),
    new AttributesDescriptor("Number", GleamSyntaxHighlighter.NUMBER),
    new AttributesDescriptor("Operation", GleamSyntaxHighlighter.OPERATION),
    new AttributesDescriptor("Comment", GleamSyntaxHighlighter.COMMENT)
  )
}

class GleamColorSettingsPage extends ColorSettingsPage {
  override def getIcon: Icon = GleamIcon.file

  override def getHighlighter: SyntaxHighlighter = new GleamSyntaxHighlighter()

  override def getDemoText: String =
    s"""
       |import gleam/io
       |import lib.{type Greeting, callback}
       |
       |// main function
       |pub fn main() {
       |  let calc = 1 + 2 * 4 - 2 / 9
       |  let hi: Greeting = "hello" <> "friend"
       |
       |  let callable =
       |    hi |> callback
       |
       |  let result = case callable {
       |    Ok(val) -> "ok:" <> val
       |    Error(Nil) -> "not ok"
       |  }
       |
       |  io.println(result)
       |}
       |
       |type User {
       |  Person(name: String)
       |  Robot(number: Int)
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
