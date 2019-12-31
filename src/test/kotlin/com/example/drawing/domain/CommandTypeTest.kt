package com.example.drawing.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

import org.junit.jupiter.api.Test

class CommandTypeTest {
  @Test
  fun validParseTest() {
    assertEquals(CommandType.CREATE, CommandType.parse("C"))
    assertEquals(CommandType.LINE, CommandType.parse("L"))
    assertEquals(CommandType.RECTANGLE, CommandType.parse("R"))
    assertEquals(CommandType.BUCKET_FILL, CommandType.parse("B"))
    assertEquals(CommandType.QUIT, CommandType.parse("Q"))
  }

  @Test
  fun invalidParseTest() {
    assertThrows(IllegalArgumentException::class.java) {
      CommandType.parse("12")
    }
  }
}