package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.EMPTY_CHAR
import com.example.drawing.domain.Canvas.Companion.LINE_CHAR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows

class BucketFillCommandTest {
  companion object {
    const val COLOR_CHAR = 'z'
  }

  @Test
  fun emptyArgsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf(), Canvas(2, 2))
    }
  }

  @Test
  fun invalidArgsSizeTest() {
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("5"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidColorArgTest() {
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "1", "qwe"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidCoordsTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("0", "1", "z"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "6", "z"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("ss", "2", "z"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "3.4", "z"), canvas)
    }
  }

  @Test
  fun outOfCanvasTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "1", "1", "3"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("0", "1", "0", "2"), canvas)
    }
  }

  @Test
  fun executeTest() {
    val canvas = Canvas(5, 3)
    val rectangleCommand = RectangleCommand(listOf("2", "1", "4", "3"), canvas)
    rectangleCommand.execute()
    val bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    bucketFillCommand.execute()

    assertEquals(COLOR_CHAR, canvas.getNode(1, 1))
    assertEquals(LINE_CHAR, canvas.getNode(2, 1))
    assertEquals(LINE_CHAR, canvas.getNode(3, 1))
    assertEquals(LINE_CHAR, canvas.getNode(4, 1))

    assertEquals(COLOR_CHAR, canvas.getNode(1, 2))
    assertEquals(LINE_CHAR, canvas.getNode(2, 2))
    assertEquals(EMPTY_CHAR, canvas.getNode(3, 2))
    assertEquals(LINE_CHAR, canvas.getNode(4, 2))

    assertEquals(COLOR_CHAR, canvas.getNode(1, 3))
    assertEquals(LINE_CHAR, canvas.getNode(2, 3))
    assertEquals(LINE_CHAR, canvas.getNode(3, 3))
    assertEquals(LINE_CHAR, canvas.getNode(4, 3))
  }

  @Test
  fun toStringTest()
  {
    val canvas = Canvas(5, 3)
    val bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    bucketFillCommand.execute()

    assertEquals(buildString {
      appendln("       ")
      appendln(" zzzzz ")
      appendln(" zzzzz ")
      appendln(" zzzzz ")
      append("       ")
    }, canvas.toString())
  }
}