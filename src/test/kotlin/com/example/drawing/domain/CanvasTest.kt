package com.example.drawing.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CanvasTest {
  @Test
  fun toStringTest() {
    val canvas = Canvas(width = 2, height = 2)
    val blankCanvas = "  \n  "
    assertEquals(canvas.toString(), blankCanvas)
  }
}