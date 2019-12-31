package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.CanvasChar
import com.example.drawing.domain.Point

class LineCommand(args: List<String>, private val canvas: Canvas) : Command {
  private val start: Point
  private val end: Point
  private val color: Char = CanvasChar.LINE_CHAR

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 4) { "There are must be 4 arguments: x1, y1, x2, y2" }

    start = Point(args[0], args[1])
    end = Point(args[2], args[3])

    require(canvas.contains(start)) { "Point (x1, y1) is out of canvas" }
    require(canvas.contains(end)) { "Point (x2, y2) is out of canvas" }
    require(start.x <= end.x) { "x2 must be greater than x1" }
    require(start.y <= end.y) { "y2 must be greater than y1" }
    require(start.x == end.x || start.y == end.y) { "Inclined lines are not supported" }
  }

  override fun execute() {
    if(start.x == end.x) {
      drawHorizontalLine()
    }
    else if(start.y == end.y) {
      drawVerticalLine()
    }
  }

  private fun drawHorizontalLine() {
    for(y in start.y .. end.y) {
      canvas.setChar(start.x, y, color)
    }
  }

  private fun drawVerticalLine() {
    for(x in start.x .. end.x) {
      canvas.setChar(x, start.y, color)
    }
  }
}