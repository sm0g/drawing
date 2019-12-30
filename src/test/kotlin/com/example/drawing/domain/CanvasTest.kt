package com.example.drawing.domain

import com.example.drawing.command.BucketFillCommand
import com.example.drawing.command.CreateCommand
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
  fun setVerticalCharTest() {
    val canvas = Canvas(2, 2)
    canvas.setVerticalChar(1, 1)

    assertEquals(VERTICAL_CHAR, canvas.getNode(1, 1))
  }

  @Test
  fun setHorizontalCharTest() {
    val canvas = Canvas(2, 2)
    canvas.setHorizontalChar(1, 1)

    assertEquals(HORIZONTAL_CHAR, canvas.getNode(1, 1))
  }

  @Test
  fun setEmptyCharTest() {
    val canvas = Canvas(2, 2)
    canvas.setEmptyChar(1, 1)

    assertEquals(EMPTY_CHAR, canvas.getNode(1, 1))
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
    val createCommand = CreateCommand(listOf("20", "4"))
    var canvas = createCommand.execute()
    assertEquals(with(StringBuilder()) {
      appendln("----------------------")
      appendln("|                    |")
      appendln("|                    |")
      appendln("|                    |")
      appendln("|                    |")
      append("----------------------")
      toString()
    }, canvas.toString())

    val lineHorizontalCommand = LineCommand(listOf("1", "2", "6", "2"), canvas)
    canvas = lineHorizontalCommand.execute()
    assertEquals(with(StringBuilder()) {
      appendln("----------------------")
      appendln("|                    |")
      appendln("|xxxxxx              |")
      appendln("|                    |")
      appendln("|                    |")
      append("----------------------")
      toString()
    }, canvas.toString())

    val lineVerticalCommand = LineCommand(listOf("6", "3", "6", "4"), canvas)
    canvas = lineVerticalCommand.execute()
    assertEquals(with(StringBuilder()) {
      appendln("----------------------")
      appendln("|                    |")
      appendln("|xxxxxx              |")
      appendln("|     x              |")
      appendln("|     x              |")
      append("----------------------")
      toString()
    }, canvas.toString())

    val rectCommand = RectangleCommand(listOf("16", "1", "20", "3"), canvas)
    canvas = rectCommand.execute()
    assertEquals(with(StringBuilder()) {
      appendln("----------------------")
      appendln("|               xxxxx|")
      appendln("|xxxxxx         x   x|")
      appendln("|     x         xxxxx|")
      appendln("|     x              |")
      append("----------------------")
      toString()
    }, canvas.toString())

    val bucketFillTest = BucketFillCommand(listOf("10", "3", "o"), canvas)
    canvas = bucketFillTest.execute()
    assertEquals(with(StringBuilder()) {
      appendln("----------------------")
      appendln("|oooooooooooooooxxxxx|")
      appendln("|xxxxxxooooooooox   x|")
      appendln("|     xoooooooooxxxxx|")
      appendln("|     xoooooooooooooo|")
      append("----------------------")
      toString()
    }, canvas.toString())
  }
}