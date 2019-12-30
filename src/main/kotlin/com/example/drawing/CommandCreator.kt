package com.example.drawing

import com.example.drawing.command.*
import com.example.drawing.domain.Canvas

class CommandCreator {
  fun createCommand(args: List<String>, canvas: Canvas?): ICommand {
    val command = args.first()
    val commandArgs = args.drop(1)

    return when(command.toUpperCase()) {
      "C" -> CreateCommand(commandArgs)
      "L" -> LineCommand(commandArgs, canvas)
      "R" -> RectangleCommand(commandArgs, canvas)
      "B" -> BucketFillCommand(commandArgs, canvas)
      "Q" -> QuitCommand()
      else -> throw IllegalArgumentException("Unknown command: $command")
    }
  }
}