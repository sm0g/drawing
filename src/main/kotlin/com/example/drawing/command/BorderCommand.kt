package com.example.drawing.command

import com.example.drawing.domain.Canvas

class BorderCommand(private val canvas: Canvas) : Command {
  private val horizontalChar = '-' 
  private val verticalChar = '|'

  override fun execute() {
    for (y in 0 until canvas.height) {
      canvas.setChar(0, y, verticalChar)
      canvas.setChar(canvas.width - 1, y, verticalChar)
    }

    for (x in 0 until canvas.width) {
      canvas.setChar(x, 0, horizontalChar)
      canvas.setChar(x, canvas.height - 1, horizontalChar)
    }
  }
}