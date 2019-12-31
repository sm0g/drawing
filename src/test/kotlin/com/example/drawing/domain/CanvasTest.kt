package com.example.drawing.domain

import com.example.drawing.command.BucketFillCommand
import com.example.drawing.command.BorderCommand
import com.example.drawing.command.LineCommand
import com.example.drawing.command.RectangleCommand
import com.example.drawing.domain.CanvasChar.Companion.EMPTY_CHAR
import com.example.drawing.domain.CanvasChar.Companion.LINE_CHAR
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

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 0))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 0))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 0))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 0))

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 1))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 1))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 1))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 1))

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 2))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 2))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 2))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 2))

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 3))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 3))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 3))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 3))
  }

  @Test
  fun newCanvasTest() {
    val canvas = Canvas(2, 2)

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 0))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 0))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 0))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 0))

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 1))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 1))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 1))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 1))

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 2))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 2))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 2))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 2))

    assertEquals(EMPTY_CHAR, canvas.getChar(0, 3))
    assertEquals(EMPTY_CHAR, canvas.getChar(1, 3))
    assertEquals(EMPTY_CHAR, canvas.getChar(2, 3))
    assertEquals(EMPTY_CHAR, canvas.getChar(3, 3))
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
    assertEquals(buildString {
      appendln("    ")
      appendln("    ")
      appendln("    ")
      append("    ")
    }, canvas.toString())
  }

  @Test
  fun getterSetterTest() {
    val canvas = Canvas(2, 2)
    canvas.setChar(1, 1, LINE_CHAR)

    assertEquals(LINE_CHAR, canvas.getChar(1, 1))
  }

  @Test
  fun equalsCharTest()
  {
    val canvas = Canvas(2, 2)
    canvas.setChar(1, 1, LINE_CHAR)
    assertTrue(canvas.equalsChar(Point(1, 1), LINE_CHAR))
  }

  @Test
  fun complexDrawingTest()
  {
    val canvas = Canvas(listOf("20", "4"))

    val createCommand = BorderCommand(canvas)
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

    var bucketFillTest = BucketFillCommand(listOf("10", "3", "o"), canvas)
    bucketFillTest.execute()
    assertEquals("""
      ----------------------
      |oooooooooooooooxxxxx|
      |xxxxxxooooooooox   x|
      |     xoooooooooxxxxx|
      |     xoooooooooooooo|
      ----------------------
    """.trimIndent(), canvas.toString())

    bucketFillTest = BucketFillCommand(listOf("1", "3", "z"), canvas)
    bucketFillTest.execute()
    assertEquals("""
      ----------------------
      |oooooooooooooooxxxxx|
      |xxxxxxooooooooox   x|
      |zzzzzxoooooooooxxxxx|
      |zzzzzxoooooooooooooo|
      ----------------------
    """.trimIndent(), canvas.toString())

    bucketFillTest = BucketFillCommand(listOf("10", "2", " "), canvas)
    bucketFillTest.execute()
    assertEquals("""
      ----------------------
      |               xxxxx|
      |xxxxxx         x   x|
      |zzzzzx         xxxxx|
      |zzzzzx              |
      ----------------------
    """.trimIndent(), canvas.toString())

    bucketFillTest = BucketFillCommand(listOf("18", "1", "a"), canvas)
    bucketFillTest.execute()
    assertEquals("""
      ----------------------
      |               aaaaa|
      |xxxxxx         a   a|
      |zzzzzx         aaaaa|
      |zzzzzx              |
      ----------------------
    """.trimIndent(), canvas.toString())
  }
}