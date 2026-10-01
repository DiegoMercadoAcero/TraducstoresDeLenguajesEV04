/*
DIEGO ARMANDO MERCADO ACERO
HECTOR ALEJANDRO NUNEZ DELGADO
ANDRES JOSUE HERNANDEZ VELARDE
LEONARDO ALEXEI FONSECA NAVA
*/
package analizadores;

import java_cup.runtime.Symbol;
import analizadores.sinctactico_archivos.sym;

%%

%class JavascriptLexer
%public
%unicode
%cup
%line
%column

%{
  private Symbol token(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
  }
  private Symbol token(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }
%}

/* Expresiones Regulares Base */
LineTerminator     = \r|\n|\r\n
InputCharacter     = [^\r\n]
WhiteSpace         = {LineTerminator} | [ \t\f]
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment   = "//" {InputCharacter}* {LineTerminator}?
Comment            = {TraditionalComment} | {EndOfLineComment}

Identifier         = [a-zA-Z_$][a-zA-Z0-9_$]*
DecIntegerLiteral  = 0 | [1-9][0-9]*
FloatLiteral       = [0-9]+\.[0-9]+([eE][+-]?[0-9]+)?
StringLiteral      = \"([^\"\\\r\n]|\\.)*\" | '([^'\\\r\n]|\\.)*'

/* Macros de Errores */
UnclosedString     = \"([^\"\\\r\n]|\\.)*[\r\n] | '([^'\\\r\n]|\\.)*[\r\n]
MalformedId        = [0-9]+[a-zA-Z_$][a-zA-Z0-9_$]*
MalformedFloat     = [0-9]+\.[0-9]+\.[0-9\.]*

%%

{WhiteSpace}  { /* Ignorar */ }
{Comment}     { /* Ignorar */ }

/* Palabras Reservadas para Métodos / Funciones en JavaScript */
"function"    { return token(sym.FUNCTION); }
"funcion"     { return token(sym.FUNCION); }
"return"      { return token(sym.RETURN); }
"retornar"    { return token(sym.RETORNAR); }

/* Variables y Estructuras JS */
"let"         { return token(sym.LET); }
"var"         { return token(sym.VAR); }
"const"       { return token(sym.CONST); }

/* Estructuras de Control */
"si" | "if"           { return token(sym.SI); }
"sino" | "else"       { return token(sym.SINO); }
"segun" | "switch"    { return token(sym.SEGUN); }
"caso" | "case"       { return token(sym.CASO); }
"defecto" | "default" { return token(sym.DEFECTO); }
"romper" | "break"    { return token(sym.ROMPER); }
"mientras" | "while"  { return token(sym.MIENTRAS); }
"hacer" | "do"        { return token(sym.HACER); }
"para" | "for"        { return token(sym.PARA); }

/* Sentencias Básicas */
"leer"        { return token(sym.LEER); }
"escribir"    { return token(sym.ESCRIBIR); }

/* Literales e Identificadores */
"true"|"false"      { return token(sym.BOOLEAN_LITERAL, yytext()); }
"null"              { return token(sym.NULL_LITERAL); }
{FloatLiteral}      { return token(sym.FLOAT_LITERAL, yytext()); }
{DecIntegerLiteral} { return token(sym.INTEGER_LITERAL, yytext()); }
{StringLiteral}     { return token(sym.STRING_LITERAL, yytext()); }
{Identifier}        { return token(sym.IDENTIFIER, yytext()); }

/* Operadores Relacionales, Lógicos y Aritméticos */
"=="          { return token(sym.EQ); }
"!="          { return token(sym.NOT_EQ); }
">="          { return token(sym.GREATER_EQ); }
"<="          { return token(sym.LESS_EQ); }
">"           { return token(sym.GREATER); }
"<"           { return token(sym.LESS); }
"&&"          { return token(sym.AND); }
"||"          { return token(sym.OR); }
"!"           { return token(sym.NOT); }
"+"           { return token(sym.PLUS); }
"-"           { return token(sym.MINUS); }
"*"           { return token(sym.MULT); }
"/"           { return token(sym.DIV); }
"%"           { return token(sym.MOD); }

/* Delimitadores y Asignación */
"="           { return token(sym.ASSIGN); }
";"           { return token(sym.SEMICOLON); }
","           { return token(sym.COMMA); }
"."           { return token(sym.DOT); }
":"           { return token(sym.COLON); }
"{"           { return token(sym.LBRACE); }
"}"           { return token(sym.RBRACE); }
"("           { return token(sym.LPAREN); }
")"           { return token(sym.RPAREN); }

/* Manejo de Errores Léxicos */
{UnclosedString} {
    ManejadorErrores.registrarError(yyline + 1, yycolumn + 1, yytext().trim(), "Cadena Mal Formada", "Cadena no cerrada.");
}
{MalformedId} {
    ManejadorErrores.registrarError(yyline + 1, yycolumn + 1, yytext(), "Identificador Mal Formado", "No puede iniciar con número.");
}
{MalformedFloat} {
    ManejadorErrores.registrarError(yyline + 1, yycolumn + 1, yytext(), "Número Incorrecto", "Formato flotante inválido.");
}

. {
    ManejadorErrores.registrarError(yyline + 1, yycolumn + 1, yytext(), "Carácter No Reconocido", "Símbolo fuera del alfabeto.");
}