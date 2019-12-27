package com.example.drawing.domain

import java.lang.StringBuilder

class Canvas(innerWidth: Int, innerHeight: Int) {
  companion object {
    const val HORIZONTAL_SYMBOL = '-'
    const val VERTICAL_SYMBOL = '|'
    const val EMPTY_SYMBOL = ' '
    const val LINE_SYMBOL = 'x'
  }

  private val width: Int
  private val height: Int
  private val nodes: Array<CharArray>

  init {
    val doubleBorderWidth = 2

    width = innerWidth + doubleBorderWidth
    height = innerHeight + doubleBorderWidth
    this.nodes = Array(this.height) {
      CharArray(this.width) { EMPTY_SYMBOL }
    }

    drawBorder()
  }

  private fun drawBorder()
  {
    for (y in 0 until height) {
      for (x in 0 until width) {
        if(y == 0 || y == height - 1) {
          setNode(x, y, HORIZONTAL_SYMBOL)
        }
        else if(x == 0 || x == width - 1) {
          setNode(x, y, VERTICAL_SYMBOL)
        }
        else {
          setNode(x, y, EMPTY_SYMBOL)
        }
      }
    }
  }

  fun getNode(x: Int, y: Int): Char {
    return nodes[x][y]
  }

  fun setNode(x: Int, y: Int, symbol: Char) {
    nodes[x][y] = symbol
  }

  fun containsPoint(point: Point): Boolean
  {
    return point.x > 0 && point.x < width - 1
        && point.y > 0 && point.y < height - 1
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