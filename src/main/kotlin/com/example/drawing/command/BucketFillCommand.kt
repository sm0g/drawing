package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Point
import java.util.*
import kotlin.collections.HashSet

class BucketFillCommand(args: List<String>, private val canvas: Canvas) : Command {
  private val eyeDrop: Point
  private val color: Char

  init {
    require(args.isNotEmpty()) { "Command arguments must not be empty" }
    require(args.size == 3) { "There are must be 3 arguments: x, y, c" }

    eyeDrop = Point(args[0], args[1])

    require(canvas.contains(eyeDrop)) { "Point (x1, y1) is out of canvas" }
    require(args[2].length == 1) { "Invalid color symbol" }

    color = args[2].toCharArray().first()
  }

  override fun execute() {
    val queue = ArrayDeque<Point>()
    queue.offer(eyeDrop)
    val visitedPoints = HashSet<Point>()
    val targetChar = canvas.getChar(eyeDrop.x, eyeDrop.y)

    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()

      if(!visitedPoints.add(current)
        || current.x == 0 || current.x == canvas.width - 1
        || current.y == 0 || current.y == canvas.height - 1
        || !canvas.equalsChar(current, targetChar)) {
        continue
      }

      canvas.setChar(current, color)
      queue.offer(Point(current.x - 1, current.y))
      queue.offer(Point(current.x + 1, current.y))
      queue.offer(Point(current.x, current.y - 1))
      queue.offer(Point(current.x, current.y + 1))
    }
  }
}