package com.example.drawing.domain

import java.lang.StringBuilder

class Canvas(val width: Int, val height: Int) {
  companion object {
    const val HORIZONTAL_SYMBOL = '-'
    const val VERTICAL_SYMBOL = '|'
    const val EMPTY_SYMBOL = ' '
  }

  val nodes: Array<CharArray>

  init {
    this.nodes = Array(this.height) {
      CharArray(this.width) { EMPTY_SYMBOL }
    }
  }

  override fun toString(): String {
    val toRet = StringBuilder()

    for (i in 0 until height) {
      for (j in 0 until width) {
        toRet.append(nodes[j][i])
      }

      if(i != height - 1) {
        toRet.appendln()
      }
    }

    return toRet.toString()
  }
}