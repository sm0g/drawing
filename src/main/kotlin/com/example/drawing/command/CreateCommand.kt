package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.utils.toPositiveIntParam

class CreateCommand(args: List<String>) : ICommand {
  private val width: Int
  private val height: Int

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 2) { "Width and Height arguments are required" }

    width = args.first().toPositiveIntParam()
    height = args.last().toPositiveIntParam()
  }

  override fun execute(): Canvas {
    return Canvas(listOf())
  }
}