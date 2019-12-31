package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.CanvasChar
import com.example.drawing.domain.Point

class RectangleCommand(args: List<String>, private val canvas: Canvas) : Command {
  private val topLeft: Point
  private val bottomRight: Point
  private val color: Char = CanvasChar.LINE_CHAR

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 4) { "There are must be 4 arguments: x1, y1, x2, y2" }

    topLeft = Point(args[0], args[1])
    bottomRight = Point(args[2], args[3])

    require(canvas.contains(topLeft)) { "Top left corner (x1, y1) is out of canvas" }
    require(canvas.contains(bottomRight)) { "Bottom right corner (x2, y2) is out of canvas" }
    require(topLeft.x < bottomRight.x && topLeft.y < bottomRight.y) { "Bottom right corner has wrong coordinates: x2 < x1 or y2 < y1" }
  }

  override fun execute() {
    val topRight = Point(bottomRight.x, topLeft.y)
    val bottomLeft = Point(topLeft.x, bottomRight.y)

    // top side
    drawHorizontalLine(topLeft, topRight)
    // bottom side
    drawHorizontalLine(bottomLeft, bottomRight)
    // left side
    drawVerticalLine(topLeft, bottomLeft)
    // right side
    drawVerticalLine(topRight, bottomRight)
  }

  private fun drawHorizontalLine(start: Point, end: Point) {
    for(x in start.x .. end.x) {
      canvas.setChar(x, start.y, color)
    }
  }

  private fun drawVerticalLine(start: Point, end: Point) {
    for(y in start.y .. end.y) {
      canvas.setChar(start.x, y, color)
    }
  }
}