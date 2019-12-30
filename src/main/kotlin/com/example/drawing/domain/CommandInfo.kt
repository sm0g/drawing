package com.example.drawing.domain

class CommandInfo(input: String) {
  val commandType: CommandType
  val commandArgs: List<String>

  init {
    val args = input.split(' ')
    val commandInput = args.firstOrNull()
    require(commandInput != null) { "Unable to parse command" }

    commandType = CommandType.parse(commandInput)
    commandArgs = args.drop(1)
  }
}