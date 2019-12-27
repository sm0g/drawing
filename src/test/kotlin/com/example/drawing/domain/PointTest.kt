package com.example.drawing.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PointTest {
  @Test
  fun stringArgumentsTest() {
    var point = Point("1", "2")
    assertEquals(1, point.x)
    assertEquals(2, point.y)

    Assertions.assertThrows(IllegalArgumentException::class.java) {
      point = Point("1", "asd")
    }

    Assertions.assertThrows(IllegalArgumentException::class.java) {
      point = Point("-4", "3")
    }

    Assertions.assertThrows(IllegalArgumentException::class.java) {
      point = Point("", "3")
    }
  }
}