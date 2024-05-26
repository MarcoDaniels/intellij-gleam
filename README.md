# IntelliJ plugin for Gleam Language

Providing support for the [Gleam language](https://gleam.run) in Intellij IDEA.

## Features

This plugin uses [Language Server Protocol - LSP](https://gleam.run/news/v0.21-introducing-the-gleam-language-server/)
for the majority of the features needed to
use `Gleam` - [Supported IDEs](https://plugins.jetbrains.com/docs/intellij/language-server-protocol.html#supported-ides)

The lexer is only used to create language highlight on the editor.

### Development

1. Open `build.sbt` in IntelliJ IDEA as a project.
2. Generate parser code
    - Run `Tools > Generate Parser Code` with [`Gleam.bnf`](src/main/scala/intellij/gleam/lang/Gleam.bnf)
    - It will generate the code for `GleamTypes.java`.
3. Generate Lexer
    - Run `Tools > Generate JFlex Lexer` with [`Gleam.bnf`](src/main/scala/intellij/gleam/lang/Gleam.bnf)
    - It will generate and override `_GleamLexer.flex`.
4. Create Lexer file
    - Run `Tools > Run JFlex Generator` with [`GleamLexer.flex`](src/main/scala/intellij/gleam/lang/_GleamLexer.flex)
    - It will generate `_GleamLexer.java`.
5. Run/Debug plugin with the `gleam` run configuration.
