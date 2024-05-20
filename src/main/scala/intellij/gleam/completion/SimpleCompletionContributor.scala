package intellij.gleam.completion

import com.intellij.codeInsight.completion._
import com.intellij.codeInsight.lookup.{LookupElement, LookupElementBuilder}
import com.intellij.lang.properties.parsing.PropertiesTokenTypes.VALUE_CHARACTERS
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

import scala.jdk.CollectionConverters.IterableHasAsJava

class SimpleCompletionContributor() extends CompletionContributor {

  extend(
    CompletionType.BASIC,
    PlatformPatterns.psiElement(VALUE_CHARACTERS),
    new CompletionProvider[CompletionParameters]() {
      override def addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          result: CompletionResultSet
      ): Unit = {
        def el =
          List(
            LookupElementBuilder.create("Cheese"),
            LookupElementBuilder.create("Cheeeese").withLookupString("="),
            LookupElementBuilder.create("Cheeeeese")
          )

        result
          .addAllElements(el.asJavaCollection)
      }

    }
  )

}
