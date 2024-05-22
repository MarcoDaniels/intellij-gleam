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

NUMBER_VALUE=[0-9]+(\.[0-9]*)?
LOWER_CASE_TERM=[:lowercase:][a-zA-Z_0-9]*
UPPER_CASE_TERM=[:uppercase:][a-zA-Z_0-9]*
STRING_VALUE=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
LINE_COMMENT=("//")[^\r\n]*
WHITE_SPACE=[\s\r\n]*

%%
<YYINITIAL> {
  {WHITE_SPACE}           { return WHITE_SPACE; }

  "import"                { return IMPORT; }
  "let"                   { return LET; }
  "pub"                   { return PUB; }
  "fn"                    { return FN; }
  "use"                   { return USE; }
  "case"                  { return CASE; }
  "type"                  { return TYPE; }
  "->"                    { return RIGHT_ARROW; }
  "<-"                    { return LEFT_ARROW; }
  "<>"                    { return CONCAT; }
  "|>"                    { return PIPE; }
  "#"                     { return TUPLE; }
  "/"                     { return SLASH; }
  "="                     { return EQUAL; }
  ":"                     { return COLON; }
  ","                     { return COMMA; }
  "."                     { return DOT; }
  "_"                     { return UNDERSCORE; }
  "("                     { return LEFT_PARENTHESIS; }
  ")"                     { return RIGHT_PARENTHESIS; }
  "{"                     { return LEFT_BRACKET; }
  "}"                     { return RIGHT_BRACKET; }
  "["                     { return LEFT_ARRAY_BRACKET; }
  "]"                     { return RIGHT_ARRAY_BRACKET; }

  {NUMBER_VALUE}          { return NUMBER_VALUE; }
  {LOWER_CASE_TERM}       { return LOWER_CASE_TERM; }
  {UPPER_CASE_TERM}       { return UPPER_CASE_TERM; }
  {STRING_VALUE}          { return STRING_VALUE; }
  {LINE_COMMENT}          { return LINE_COMMENT; }
  {WHITE_SPACE}           { return WHITE_SPACE; }

}

[^] { return BAD_CHARACTER; }
