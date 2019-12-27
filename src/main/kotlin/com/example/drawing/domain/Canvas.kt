package com.example.drawing.domain

import java.lang.StringBuilder

class Canvas(val width: Int, val height: Int) {
  companion object {
    const val HORIZONTAL_SYMBOL = '-'
    const val VERTICAL_SYMBOL = '|'
    const val EMPTY_SYMBOL = ' '
  }

  private val nodes: Array<CharArray>

  init {
    this.nodes = Array(this.height) {
      CharArray(this.width) { EMPTY_SYMBOL }
    }
  }

  fun getNode(x: Int, y: Int): Char {
    return nodes[x][y]
  }

  fun setNode(x: Int, y: Int, symbol: Char) {
    nodes[x][y] = symbol
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