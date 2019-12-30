package com.example.drawing.domain

import com.example.drawing.command.BucketFillCommand
import com.example.drawing.command.EraseCommand
import com.example.drawing.command.LineCommand
import com.example.drawing.command.RectangleCommand
import com.example.drawing.domain.Canvas.Companion.EMPTY_CHAR
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_CHAR
import com.example.drawing.domain.Canvas.Companion.LINE_CHAR
import com.example.drawing.domain.Canvas.Companion.VERTICAL_CHAR
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CanvasTest {
  @Test
  fun newCanvasInvalidArgsListTest() {
    assertThrows(IllegalArgumentException::class.java) {
      Canvas(listOf("2"))
    }
    assertThrows(IllegalArgumentException::class.java) {
      Canvas(listOf("2", "dsd"))
    }
    assertThrows(IllegalArgumentException::class.java) {
      Canvas(listOf("2", "-2"))
    }
  }

  @Test
  fun newCanvasArgsListTest() {
    val canvas = Canvas(listOf("2", "2"))

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
  fun newCanvasTest() {
    val canvas = Canvas(2, 2)

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
  fun containsPointTest() {
    val canvas = Canvas(2, 2)
    assert(canvas.contains(Point(1, 1)))
    assert(canvas.contains(Point(2, 2)))
    assertFalse(canvas.contains(Point(1, 0)))
    assertFalse(canvas.contains(Point(0, 1)))
    assertFalse(canvas.contains(Point(3, 1)))
    assertFalse(canvas.contains(Point(1, 3)))
  }

  @Test
  fun toStringTest()
  {
    val canvas = Canvas(2, 2)
    assertEquals(with(StringBuilder()) {
      appendln("----")
      appendln("|  |")
      appendln("|  |")
      append("----")
      toString()
    }, canvas.toString())
  }

  @Test
  fun getterSetterTest() {
    val canvas = Canvas(2, 2)
    canvas.setNode(1, 1, VERTICAL_CHAR)

    assertEquals(VERTICAL_CHAR, canvas.getNode(1, 1))
  }

  @Test
  fun setLineCharTest() {
    val canvas = Canvas(2, 2)
    canvas.setLineChar(1, 1)

    assertEquals(LINE_CHAR, canvas.getNode(1, 1))
  }

  @Test
  fun equalsCharTest()
  {
    val canvas = Canvas(2, 2)
    canvas.setLineChar(1, 1)
    assertTrue(canvas.equalsChar(Point(1, 1), LINE_CHAR))
  }

  @Test
  fun complexDrawingTest()
  {
    val canvas = Canvas(listOf("20", "4"))

    val createCommand = EraseCommand(canvas)
    createCommand.execute()
    assertEquals("""
      ----------------------
      |                    |
      |                    |
      |                    |
      |                    |
      ----------------------
    """.trimIndent(), canvas.toString())

    val lineHorizontalCommand = LineCommand(listOf("1", "2", "6", "2"), canvas)
    lineHorizontalCommand.execute()
    assertEquals("""
      ----------------------
      |                    |
      |xxxxxx              |
      |                    |
      |                    |
      ----------------------
    """.trimIndent(), canvas.toString())

    val lineVerticalCommand = LineCommand(listOf("6", "3", "6", "4"), canvas)
    lineVerticalCommand.execute()
    assertEquals("""
      ----------------------
      |                    |
      |xxxxxx              |
      |     x              |
      |     x              |
      ----------------------
    """.trimIndent(), canvas.toString())

    val rectCommand = RectangleCommand(listOf("16", "1", "20", "3"), canvas)
    rectCommand.execute()
    assertEquals("""
      ----------------------
      |               xxxxx|
      |xxxxxx         x   x|
      |     x         xxxxx|
      |     x              |
      ----------------------
    """.trimIndent(), canvas.toString())

    val bucketFillTest = BucketFillCommand(listOf("10", "3", "o"), canvas)
    bucketFillTest.execute()
    assertEquals("""
      ----------------------
      |oooooooooooooooxxxxx|
      |xxxxxxooooooooox   x|
      |     xoooooooooxxxxx|
      |     xoooooooooooooo|
      ----------------------
    """.trimIndent(), canvas.toString())
  }
}