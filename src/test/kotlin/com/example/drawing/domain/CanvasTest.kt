package com.example.drawing.domain

import com.example.drawing.domain.Canvas.Companion.EMPTY_SYMBOL
import com.example.drawing.domain.Canvas.Companion.VERTICAL_SYMBOL
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CanvasTest {
  @Test
  fun emptyNodesTest() {
    val canvas = Canvas(2, 2)
    assertEquals(EMPTY_SYMBOL, canvas.getNode(0, 0))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(0, 1))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(1, 0))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(1, 1))
  }

  @Test
  fun toStringTest()
  {
    val canvas = Canvas(2, 2)
    assertEquals("  \n  ", canvas.toString())
  }

  @Test
  fun getterSetterTest() {
    val canvas = Canvas(2, 2)
    canvas.setNode(1, 1, VERTICAL_SYMBOL)

    assertEquals(VERTICAL_SYMBOL, canvas.getNode(1, 1))
  }
}