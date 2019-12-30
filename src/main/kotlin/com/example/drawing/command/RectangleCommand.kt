package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Point

class RectangleCommand(args: List<String>, incomingCanvas: Canvas?) : ICommand {
  private val canvas: Canvas
  private val topLeft: Point
  private val bottomRight: Point

  init {
    require(incomingCanvas != null) { "Create canvas at first" }
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 4) { "There are must be 4 arguments: x1, y1, x2, y2" }

    canvas = incomingCanvas
    topLeft = Point(args[0], args[1])
    bottomRight = Point(args[2], args[3])

    require(canvas.contains(topLeft)) { "Top left corner (x1, y1) is out of canvas" }
    require(canvas.contains(bottomRight)) { "Bottom right corner (x2, y2) is out of canvas" }
    require(topLeft.x < bottomRight.x && topLeft.y < bottomRight.y) { "Bottom right corner has wrong coordinates: x2 < x1 or y2 < y1" }
  }

  override fun execute(): Canvas {
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

    return canvas
  }

  private fun drawHorizontalLine(start: Point, end: Point) {
    for(x in start.x .. end.x) {
      canvas.setLineChar(x, start.y)
    }
  }

  private fun drawVerticalLine(start: Point, end: Point) {
    for(y in start.y .. end.y) {
      canvas.setLineChar(start.x, y)
    }
  }
}