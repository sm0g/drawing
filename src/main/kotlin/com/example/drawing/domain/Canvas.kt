package com.example.drawing.domain

import java.lang.StringBuilder

class Canvas(width: Int, height: Int) {
  companion object {
    val HORIZONTAL_SYMBOL = '-'
    val VERTICAL_SYMBOL = '|'
  }

  val width: Int
  val height: Int
  val nodes: Array<CharArray>

  init {
    val doubleBorderWidth = 2
    this.width = width + doubleBorderWidth
    this.height = height + doubleBorderWidth
    this.nodes = Array(this.height) {CharArray(this.width)}

    drawBorder(this.width, this.height)
  }

  private fun drawBorder(width: Int, height: Int) {
    for (i in 0 until height) {
      for (j in 0 until width) {
        if(i == 0 || i == height - 1) {
          nodes[j][i] = HORIZONTAL_SYMBOL
        }
        else if(j == 0 || j == width - 1) {
          nodes[j][i] = VERTICAL_SYMBOL
        }
      }
    }
  }

  override fun toString(): String {
    val toRet = StringBuilder()

    for (i in 0 until height) {
      for (j in 0 until width) {
        toRet.append(nodes[j][i])
      }

      toRet.appendln()
    }

    return toRet.toString()
  }
}