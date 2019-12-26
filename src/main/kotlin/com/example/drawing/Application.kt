package com.example.drawing

import com.example.drawing.command.CreateCommand

fun main(args: Array<String>) {
  val createCommand = CreateCommand(listOf("3", "3"))
  val blankCanvas = createCommand.execute()
  print(blankCanvas)
}