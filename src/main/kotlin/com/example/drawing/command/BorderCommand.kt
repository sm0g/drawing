package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_CHAR
import com.example.drawing.domain.Canvas.Companion.VERTICAL_CHAR

class BorderCommand(private val canvas: Canvas) : Command {
  override fun execute() {
    for (x in 0 until canvas.width) {
      if(x >= 0 && x <= canvas.width - 1) {
        canvas.setNode(x, 0, HORIZONTAL_CHAR)
        canvas.setNode(x, canvas.height - 1, HORIZONTAL_CHAR)
      }
    }

    for (y in 0 until canvas.height) {
      if(y >= 1 && y <= canvas.height - 2) {
        canvas.setNode(0, y, VERTICAL_CHAR)
        canvas.setNode(canvas.width - 1, y, VERTICAL_CHAR)
      }
    }
  }
}