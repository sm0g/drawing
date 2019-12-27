package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.LINE_SYMBOL
import com.example.drawing.domain.Point
import com.example.drawing.utils.toPositiveIntParam

class LineCommand(args: List<String>, private val canvas: Canvas) : ICommand {
  private val start: Point
  private val end: Point

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 4) { "There are must be 4 arguments: x1, y1, x2, y2" }

    start = Point(
      x = args[0].toPositiveIntParam(),
      y = args[1].toPositiveIntParam()
    )

    end = Point(
      x = args[2].toPositiveIntParam(),
      y = args[3].toPositiveIntParam()
    )

    require(canvas.containsPoint(start)) { "Point (x1, y1) is out of canvas" }
    require(canvas.containsPoint(end)) { "Point (x2, y2) is out of canvas" }
    require(start.x == end.x || start.y == end.y) { "Inclined lines are not supported" }
  }

  override fun execute(): Canvas {
    if(start.x == end.x) {
      return drawHorizontalLine()
    }

    if(start.y == end.y) {
      return drawVerticalLine()
    }

    return canvas
  }

  private fun drawHorizontalLine(): Canvas {
    for(y in start.y .. end.y) {
      canvas.setNode(start.x, y, LINE_SYMBOL)
    }

    return canvas
  }

  private fun drawVerticalLine(): Canvas {
    for(x in start.x .. end.x) {
      canvas.setNode(x, start.y, LINE_SYMBOL)
    }

    return canvas
  }
}