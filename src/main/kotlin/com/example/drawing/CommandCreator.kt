package com.example.drawing

import com.example.drawing.command.*
import com.example.drawing.domain.Canvas
import com.example.drawing.domain.CommandInfo
import com.example.drawing.domain.CommandType

class CommandCreator {
  fun createCommand(commandInfo: CommandInfo, canvas: Canvas): ICommand {
    return when(commandInfo.commandType) {
      CommandType.CREATE_CANVAS -> CreateCommand(commandInfo.commandArgs)
      CommandType.LINE -> LineCommand(commandInfo.commandArgs, canvas)
      CommandType.RECTANGLE -> RectangleCommand(commandInfo.commandArgs, canvas)
      CommandType.BUCKET_FILL -> BucketFillCommand(commandInfo.commandArgs, canvas)
      CommandType.QUIT -> QuitCommand()
    }
  }
}