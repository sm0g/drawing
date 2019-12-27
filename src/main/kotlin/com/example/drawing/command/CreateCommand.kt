package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.EMPTY_SYMBOL
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_SYMBOL
import com.example.drawing.domain.Canvas.Companion.VERTICAL_SYMBOL
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

    for (y in 0 until height) {
      for (x in 0 until width) {
        if(y == 0 || y == height - 1) {
          canvas.setNode(x, y, HORIZONTAL_SYMBOL)
        }
        else if(x == 0 || x == width - 1) {
          canvas.setNode(x, y, VERTICAL_SYMBOL)
        }
        else {
          canvas.setNode(x, y, EMPTY_SYMBOL)
        }
      }
    }

    return canvas
  }
}