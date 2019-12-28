package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_CHAR
import com.example.drawing.domain.Canvas.Companion.LINE_CHAR
import com.example.drawing.domain.Canvas.Companion.VERTICAL_CHAR
import com.example.drawing.domain.Point
import java.util.*
import kotlin.collections.HashSet

class BucketFillCommand(args: List<String>, incomingCanvas: Canvas?) : ICommand {
  private val canvas: Canvas
  private val eyeDrop: Point
  private val color: Char

  init {
    require(incomingCanvas != null) { "Create canvas at first" }
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 3) { "There are must be 3 arguments: x, y, c" }

    canvas = incomingCanvas
    eyeDrop = Point(args[0], args[1])

    require(canvas.contains(eyeDrop)) { "Point (x1, y1) is out of canvas" }
    require(args[2].length == 1) { "Invalid color symbol" }

    color = args[2].toCharArray().first()
  }

  override fun execute(): Canvas {
    val queue = ArrayDeque<Point>()
    queue.offer(eyeDrop)
    val visitedPoints = HashSet<Point>()

    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()

      if(!visitedPoints.add(current)
        || canvas.equalsChar(current, HORIZONTAL_CHAR)
        || canvas.equalsChar(current, VERTICAL_CHAR)
        || canvas.equalsChar(current, LINE_CHAR)) {
        continue
      }

      canvas.setNode(current, color)
      queue.offer(Point(current.x - 1, current.y))
      queue.offer(Point(current.x + 1, current.y))
      queue.offer(Point(current.x, current.y - 1))
      queue.offer(Point(current.x, current.y + 1))
    }

    return canvas
  }
}