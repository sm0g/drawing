package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.EMPTY_CHAR
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_CHAR
import com.example.drawing.domain.Canvas.Companion.VERTICAL_CHAR

class EraseCommand(private val canvas: Canvas) : Command {
  override fun execute() {
    for (y in 0 until canvas.height) {
      for (x in 0 until canvas.width) {
        if(y == 0 || y == canvas.height - 1) {
          canvas.setNode(x, y, HORIZONTAL_CHAR)
        }
        else if(x == 0 || x == canvas.width - 1) {
          canvas.setNode(x, y, VERTICAL_CHAR)
        }
        else {
          canvas.setNode(x, y, EMPTY_CHAR)
        }
      }
    }
  }
}