package com.example.drawing.domain

import com.example.drawing.domain.Canvas.Companion.EMPTY_SYMBOL
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CanvasTest {
  @Test
  fun toStringTest() {
    val canvas = Canvas(width = 2, height = 2)
    assertEquals(canvas.getNode(0, 0), EMPTY_SYMBOL)
    assertEquals(canvas.getNode(0, 1), EMPTY_SYMBOL)
    assertEquals(canvas.getNode(1, 0), EMPTY_SYMBOL)
    assertEquals(canvas.getNode(1, 1), EMPTY_SYMBOL)
  }
}