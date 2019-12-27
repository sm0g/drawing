package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Point

class LineCommand(args: List<String>, incomingCanvas: Canvas?) : ICommand {
  private val canvas: Canvas
  private val start: Point
  private val end: Point

  init {
    require(incomingCanvas != null) { "Create canvas at first" }
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 4) { "There are must be 4 arguments: x1, y1, x2, y2" }

    canvas = incomingCanvas
    start = Point(args[0], args[1])
    end = Point(args[2], args[3])

    require(canvas.contains(start)) { "Point (x1, y1) is out of canvas" }
    require(canvas.contains(end)) { "Point (x2, y2) is out of canvas" }
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
      canvas.setLineChar(start.x, y)
    }

    return canvas
  }

  private fun drawVerticalLine(): Canvas {
    for(x in start.x .. end.x) {
      canvas.setLineChar(x, start.y)
    }

    return canvas
  }
}