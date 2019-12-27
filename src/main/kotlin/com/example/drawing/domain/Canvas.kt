package com.example.drawing.domain

import java.lang.StringBuilder

class Canvas(innerWidth: Int, innerHeight: Int) {
  companion object {
    const val HORIZONTAL_CHAR = '-'
    const val VERTICAL_CHAR = '|'
    const val EMPTY_CHAR = ' '
    const val LINE_CHAR = 'x'
  }

  private val width: Int
  private val height: Int
  private val nodes: Array<CharArray>

  init {
    val doubleBorderWidth = 2

    width = innerWidth + doubleBorderWidth
    height = innerHeight + doubleBorderWidth
    this.nodes = Array(width) {
      CharArray(height) { EMPTY_CHAR }
    }

    drawBorder()
  }

  private fun drawBorder()
  {
    for (y in 0 until height) {
      for (x in 0 until width) {
        if(y == 0 || y == height - 1) {
          setHorizontalChar(x, y)
        }
        else if(x == 0 || x == width - 1) {
          setVerticalChar(x, y)
        }
        else {
          setEmptyChar(x, y)
        }
      }
    }
  }

  fun getNode(x: Int, y: Int): Char {
    return nodes[x][y]
  }

  fun setNode(point: Point, char: Char) {
    setNode(point.x, point.y, char)
  }

  fun setNode(x: Int, y: Int, char: Char) {
    nodes[x][y] = char
  }

  fun setVerticalChar(x: Int, y: Int) {
    setNode(x, y, VERTICAL_CHAR)
  }

  fun setHorizontalChar(x: Int, y: Int) {
    setNode(x, y, HORIZONTAL_CHAR)
  }

  fun setEmptyChar(x: Int, y: Int) {
    setNode(x, y, EMPTY_CHAR)
  }

  fun setLineChar(x: Int, y: Int) {
    setNode(x, y, LINE_CHAR)
  }

  fun contains(point: Point): Boolean
  {
    return contains(point.x, point.y)
  }

  fun contains(x: Int, y: Int): Boolean
  {
    return x > 0 && x < width - 1
        && y > 0 && y < height - 1
  }

  fun equalsChar(point: Point, char: Char): Boolean {
    return equalsChar(point.x, point.y, char)
  }

  fun equalsChar(x: Int, y: Int, char: Char): Boolean {
    val existingChar = getNode(x, y)
    return existingChar == char
  }

  override fun toString(): String {
    val toRet = StringBuilder()

    for (y in 0 until height) {
      for (x in 0 until width) {
        toRet.append(getNode(x, y))
      }

      if(y != height - 1) {
        toRet.appendln()
      }
    }

    return toRet.toString()
  }
}