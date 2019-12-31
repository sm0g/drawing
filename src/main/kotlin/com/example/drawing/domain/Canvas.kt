package com.example.drawing.domain

import com.example.drawing.utils.toPositiveIntParam
import java.lang.StringBuilder

class Canvas(innerWidth: Int, innerHeight: Int) {
  val width: Int
  val height: Int
  private val borderWidth: Int = 1
  private val nodes: Array<CharArray>

  constructor(args: List<String>) : this(args.first().toPositiveIntParam(), args.last().toPositiveIntParam()) {
    require(args.size == 2) { "Width and Height arguments are required" }
  }

  init {
    width = innerWidth + borderWidth * 2
    height = innerHeight + borderWidth * 2
    this.nodes = Array(width) {
      CharArray(height) { CanvasChar.EMPTY_CHAR }
    }
  }

  fun getChar(x: Int, y: Int): Char {
    return nodes[x][y]
  }

  fun setChar(point: Point, char: Char) {
    setChar(point.x, point.y, char)
  }

  fun setChar(x: Int, y: Int, char: Char) {
    nodes[x][y] = char
  }

  fun contains(point: Point): Boolean
  {
    return contains(point.x, point.y)
  }

  fun contains(x: Int, y: Int): Boolean
  {
    return x >= borderWidth && x <= width - borderWidth - 1
        && y >= borderWidth && y <= height - borderWidth - 1
  }

  fun equalsChar(point: Point, char: Char): Boolean {
    val existingChar = getChar(point.x, point.y)
    return existingChar == char
  }

  override fun toString(): String {
    val toRet = StringBuilder()

    for (y in 0 until height) {
      for (x in 0 until width) {
        toRet.append(getChar(x, y))
      }

      if(y != height - 1) {
        toRet.appendln()
      }
    }

    return toRet.toString()
  }
}