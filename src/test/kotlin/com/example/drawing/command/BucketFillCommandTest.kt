package com.example.drawing.command

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.Canvas.Companion.EMPTY_CHAR
import com.example.drawing.domain.Canvas.Companion.LINE_CHAR
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BucketFillCommandTest {
  companion object {
    const val COLOR_CHAR = 'z'
  }

  @Test
  fun emptyArgsTest() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf(), Canvas(2, 2))
    }
  }

  @Test
  fun invalidArgsSizeTest() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("5"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidColorArgTest() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "1", "qwe"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidCoordsTest() {
    val canvas = Canvas(2, 2)

    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("0", "1", "z"), canvas)
    }
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "6", "z"), canvas)
    }
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("ss", "2", "z"), canvas)
    }
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "3.4", "z"), canvas)
    }
  }

  @Test
  fun outOfCanvasTest() {
    val canvas = Canvas(2, 2)

    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "1", "1", "3"), canvas)
    }
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("0", "1", "0", "2"), canvas)
    }
  }

  @Test
  fun executeTest() {
    var canvas = Canvas(5, 3)
    val rectangleCommand = RectangleCommand(listOf("2", "1", "4", "3"), canvas)
    canvas = rectangleCommand.execute()
    val bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    canvas = bucketFillCommand.execute()

    Assertions.assertEquals(COLOR_CHAR, canvas.getNode(1, 1))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(2, 1))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(3, 1))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(4, 1))

    Assertions.assertEquals(COLOR_CHAR, canvas.getNode(1, 2))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(2, 2))
    Assertions.assertEquals(EMPTY_CHAR, canvas.getNode(3, 2))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(4, 2))

    Assertions.assertEquals(COLOR_CHAR, canvas.getNode(1, 3))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(2, 3))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(3, 3))
    Assertions.assertEquals(LINE_CHAR, canvas.getNode(4, 3))
  }

  @Test
  fun toStringTest()
  {
    var canvas = Canvas(5, 3)
    val rectangleCommand = RectangleCommand(listOf("2", "1", "4", "3"), canvas)
    canvas = rectangleCommand.execute()
    val bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    canvas = bucketFillCommand.execute()

    val expected = StringBuilder()
      .appendln("-------")
      .appendln("|zxxx |")
      .appendln("|zx x |")
      .appendln("|zxxx |")
        .append("-------")

    Assertions.assertEquals(expected.toString(), canvas.toString())
  }
}