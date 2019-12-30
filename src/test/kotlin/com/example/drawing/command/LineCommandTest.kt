package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.LINE_CHAR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class LineCommandTest {
  @Test
  fun emptyArgsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf(), Canvas(2, 2))
    }
  }

  @Test
  fun invalidArgsSizeTest() {
    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf("5"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidCoordsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf("-1", "1", "2", "2"), Canvas(2, 2))
    }
    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf("1", "sf", "2", "2"), Canvas(2, 2))
    }
  }

  @Test
  fun outOfCanvasTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf("1", "1", "1", "3"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf("0", "1", "0", "2"), canvas)
    }
  }

  @Test
  fun inclineLineTest() {
    val canvas = Canvas(2, 2)
    assertThrows(IllegalArgumentException::class.java) {
      LineCommand(listOf("1", "1", "2", "2"), canvas)
    }
  }

  @Test
  fun executeTest() {
    val canvas = Canvas(2, 2)
    val command = LineCommand(listOf("1", "1", "2", "1"), canvas)
    command.execute()

    assertEquals(LINE_CHAR, canvas.getNode(1, 1))
    assertEquals(LINE_CHAR, canvas.getNode(2, 1))
  }

  @Test
  fun toStringTest()
  {
    val canvas = Canvas(2, 2)
    val command = LineCommand(listOf("1", "1", "1", "2"), canvas)
    command.execute()

    assertEquals(buildString {
      appendln("    ")
      appendln(" x  ")
      appendln(" x  ")
      append("    ")
    }, canvas.toString())
  }
}