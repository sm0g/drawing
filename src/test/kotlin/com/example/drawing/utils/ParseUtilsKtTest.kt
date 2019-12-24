package com.example.drawing.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ParseUtilsKtTest {
  @Test
  fun validArgumentTest() {
    assertEquals(0, "0".toPositiveIntParam())
    assertEquals(10, "10".toPositiveIntParam())
  }

  @Test
  fun invalidArgumentTest() {
    assertThrows(IllegalArgumentException::class.java) { "-1".toPositiveIntParam() }
    assertThrows(IllegalArgumentException::class.java) { "3.14".toPositiveIntParam() }
    assertThrows(IllegalArgumentException::class.java) { "s".toPositiveIntParam() }
    assertThrows(IllegalArgumentException::class.java) { "".toPositiveIntParam() }
    assertThrows(IllegalArgumentException::class.java) { " ".toPositiveIntParam() }
  }
}