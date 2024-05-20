package intellij.gleam.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static intellij.gleam.gen.lang.GleamTypes.*;

%%

%{
  public _GleamLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _GleamLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

TERM=[A-Za-z]*
STRING_VALUE=(\")[^\r\n]*(\")
MULTILINE_STRING_VALUE=('')(.|\n)*('')
NUMBER_VALUE=([0-9]*\.)?[0-9]+
LINE_COMMENT=(--)[^\r\n]*
BLOCK_COMMENT=(\{-)(.|\n)*(-})
WHITE_SPACE=[\s\r\n]*

%%
<YYINITIAL> {
  {WHITE_SPACE}                  { return WHITE_SPACE; }

  "let"                          { return LET; }
  "="                            { return EQUAL; }
  ":"                            { return COLON; }
  "->"                           { return ARROW; }
  ","                            { return COMMA; }
  "."                            { return DOT; }
  "_"                            { return UNDERSCORE; }
  "("                            { return LEFT_PARENTHESIS; }
  ")"                            { return RIGHT_PARENTHESIS; }
  "{"                            { return LEFT_BRACKET; }
  "}"                            { return RIGHT_BRACKET; }
  "["                            { return LEFT_ARRAY_BRACKET; }
  "]"                            { return RIGHT_ARRAY_BRACKET; }

  {TERM}                         { return TERM; }
  {STRING_VALUE}                 { return STRING_VALUE; }
  {MULTILINE_STRING_VALUE}       { return MULTILINE_STRING_VALUE; }
  {NUMBER_VALUE}                 { return NUMBER_VALUE; }
  {LINE_COMMENT}                 { return LINE_COMMENT; }
  {BLOCK_COMMENT}                { return BLOCK_COMMENT; }
  {WHITE_SPACE}                  { return WHITE_SPACE; }

}

[^] { return BAD_CHARACTER; }
