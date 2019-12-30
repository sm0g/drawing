package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.EMPTY_CHAR
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_CHAR
import com.example.drawing.domain.Canvas.Companion.VERTICAL_CHAR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class EraseCommandTest {
  @Test
  fun executeTest() {
    val canvas = Canvas(2, 2)
    val createCommand = EraseCommand(canvas)
    createCommand.execute()

    assertEquals(HORIZONTAL_CHAR, canvas.getNode(0, 0))
    assertEquals(HORIZONTAL_CHAR, canvas.getNode(1, 0))
    assertEquals(HORIZONTAL_CHAR, canvas.getNode(2, 0))
    assertEquals(HORIZONTAL_CHAR, canvas.getNode(3, 0))

    assertEquals(VERTICAL_CHAR, canvas.getNode(0, 1))
    assertEquals(EMPTY_CHAR, canvas.getNode(1, 1))
    assertEquals(EMPTY_CHAR, canvas.getNode(2, 1))
    assertEquals(VERTICAL_CHAR, canvas.getNode(3, 1))

    assertEquals(VERTICAL_CHAR, canvas.getNode(0, 2))
    assertEquals(EMPTY_CHAR, canvas.getNode(1, 2))
    assertEquals(EMPTY_CHAR, canvas.getNode(2, 2))
    assertEquals(VERTICAL_CHAR, canvas.getNode(3, 2))

    assertEquals(HORIZONTAL_CHAR, canvas.getNode(0, 3))
    assertEquals(HORIZONTAL_CHAR, canvas.getNode(1, 3))
    assertEquals(HORIZONTAL_CHAR, canvas.getNode(2, 3))
    assertEquals(HORIZONTAL_CHAR, canvas.getNode(3, 3))
  }

  @Test
  fun toStringTest()
  {
    val createCommand = EraseCommand(Canvas(2, 2))
    val canvas = createCommand.execute()

    assertEquals(with(StringBuilder()) {
      appendln("----")
      appendln("|  |")
      appendln("|  |")
      append("----")
      toString()
    }, canvas.toString())
  }
}