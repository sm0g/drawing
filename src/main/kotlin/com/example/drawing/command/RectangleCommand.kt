package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Point
import com.example.drawing.utils.toPositiveIntParam

class RectangleCommand(args: List<String>, private val canvas: Canvas) : ICommand {
  private val topLeft: Point
  private val bottomRight: Point

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 4) { "There are must be 4 arguments: x1, y1, x2, y2" }

    topLeft = Point(
      x = args[0].toPositiveIntParam(),
      y = args[1].toPositiveIntParam()
    )

    bottomRight = Point(
      x = args[2].toPositiveIntParam(),
      y = args[3].toPositiveIntParam()
    )

    require(canvas.containsPoint(topLeft)) { "Top left corner (x1, y1) is out of canvas" }
    require(canvas.containsPoint(bottomRight)) { "Bottom right corner (x2, y2) is out of canvas" }
    require(topLeft.x < bottomRight.x && topLeft.y < bottomRight.y) { "Bottom right corner has wrong coordinates: x2 < x1 or y2 < y1" }
  }

  override fun execute(): Canvas {
    for(x in topLeft.x .. bottomRight.x) {
      for(y in topLeft.y .. bottomRight.y) {
        if(isRectSide(x, y)) {
          canvas.setLineChar(x, y)
        }
        else {
          canvas.setEmptyChar(x, y)
        }
      }
    }

    return canvas
  }

  private fun isRectSide(x: Int, y: Int): Boolean {
    return x == topLeft.x || x == bottomRight.x
        || y == topLeft.y || y == bottomRight.y
  }
}