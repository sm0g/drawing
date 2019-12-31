package com.example.drawing.command

import com.example.drawing.domain.Canvas
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class RectangleCommandTest {
  @Test
  fun emptyArgsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf(), Canvas(2, 2))
    }
  }

  @Test
  fun invalidArgsSizeTest() {
    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("5"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidCoordsTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("2", "1", "1", "1"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("1", "2", "2", "2"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("ss", "2", "2", "2"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("1", "1", "3.3", "2"), canvas)
    }
  }

  @Test
  fun outOfCanvasTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("1", "1", "1", "3"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      RectangleCommand(listOf("0", "1", "0", "2"), canvas)
    }
  }

  @Test
  fun toStringTest()
  {
    val canvas = Canvas(5, 3)
    val command = RectangleCommand(listOf("2", "1", "4", "3"), canvas)
    command.execute()
    
    assertEquals(buildString {
      appendln("       ")
      appendln("  xxx  ")
      appendln("  x x  ")
      appendln("  xxx  ")
      append("       ")
    }, canvas.toString())
  }
}