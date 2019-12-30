package com.example.drawing.command

import kotlin.system.exitProcess

class QuitCommand : Command {
  override fun execute() {
    exitProcess(0)
  }
}