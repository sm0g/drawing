package com.example.drawing.command

import com.example.drawing.domain.Canvas

class BorderCommand(private val canvas: Canvas) : Command {
  companion object {
    const val HORIZONTAL_CHAR = '-'
    const val VERTICAL_CHAR = '|'
  }

  override fun execute() {
    for (y in 0 until canvas.height) {
      canvas.setChar(0, y, VERTICAL_CHAR)
      canvas.setChar(canvas.width - 1, y, VERTICAL_CHAR)
    }

    for (x in 0 until canvas.width) {
      canvas.setChar(x, 0, HORIZONTAL_CHAR)
      canvas.setChar(x, canvas.height - 1, HORIZONTAL_CHAR)
    }
  }
}