package com.example.drawing.command

import com.example.drawing.domain.Canvas
import kotlin.system.exitProcess

class QuitCommand : ICommand {
  override fun execute(): Canvas {
    exitProcess(0)
  }
}