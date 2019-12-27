package com.example.drawing.domain

import com.example.drawing.domain.Canvas.Companion.EMPTY_CHAR
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_CHAR
import com.example.drawing.domain.Canvas.Companion.LINE_CHAR
import com.example.drawing.domain.Canvas.Companion.VERTICAL_CHAR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
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
    assert(canvas.containsPoint(Point(1, 1)))
    assert(canvas.containsPoint(Point(2, 2)))
    assertFalse(canvas.containsPoint(Point(1, 0)))
    assertFalse(canvas.containsPoint(Point(0, 1)))
    assertFalse(canvas.containsPoint(Point(3, 1)))
    assertFalse(canvas.containsPoint(Point(1, 3)))
  }
  
  @Test
  fun toStringTest()
  {
    val canvas = Canvas(2, 2)

    assertEquals("""
      ----
      |  |
      |  |
      ----
    """.trimIndent(), canvas.toString())
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
}