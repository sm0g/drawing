package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.utils.toPositiveIntParam

class CreateCommand(args: List<String>) : ICommand {
  private val width: Int
  private val height: Int

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 2) { "Width and Height arguments are required" }

    val doubleBorderWidth = 2
    width = args.first().toPositiveIntParam() + doubleBorderWidth
    height = args.last().toPositiveIntParam() + doubleBorderWidth
  }

  override fun execute(): Canvas {
    val canvas = Canvas(width, height)

    for (i in 0 until height) {
      for (j in 0 until width) {
        if(i == 0 || i == height - 1) {
          canvas.nodes[j][i] = Canvas.HORIZONTAL_SYMBOL
        }
        else if(j == 0 || j == width - 1) {
          canvas.nodes[j][i] = Canvas.VERTICAL_SYMBOL
        }
        else {
          canvas.nodes[j][i] = Canvas.EMPTY_SYMBOL
        }
      }
    }

    return canvas
  }
}