package com.example.drawing.command

import com.example.drawing.domain.Canvas.Companion.EMPTY_SYMBOL
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_SYMBOL
import com.example.drawing.domain.Canvas.Companion.VERTICAL_SYMBOL
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class CreateCommandTest {
  @Test
  fun emptyArgsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      CreateCommand(listOf())
    }
  }

  @Test
  fun invalidArgsSizeTest() {
    assertThrows(IllegalArgumentException::class.java) {
      CreateCommand(listOf("2"))
    }
  }

  @Test
  fun invalidArgsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      CreateCommand(listOf("-1", "1"))
    }
    assertThrows(IllegalArgumentException::class.java) {
      CreateCommand(listOf("1", "sf"))
    }
  }

  @Test
  fun executeTest() {
    val createCommand = CreateCommand(listOf("2", "2"))
    val canvas = createCommand.execute()

    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(0, 0))
    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(1, 0))
    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(2, 0))
    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(3, 0))

    assertEquals(VERTICAL_SYMBOL, canvas.getNode(0, 1))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(1, 1))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(2, 1))
    assertEquals(VERTICAL_SYMBOL, canvas.getNode(3, 1))

    assertEquals(VERTICAL_SYMBOL, canvas.getNode(0, 2))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(1, 2))
    assertEquals(EMPTY_SYMBOL, canvas.getNode(2, 2))
    assertEquals(VERTICAL_SYMBOL, canvas.getNode(3, 2))

    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(0, 3))
    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(1, 3))
    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(2, 3))
    assertEquals(HORIZONTAL_SYMBOL, canvas.getNode(3, 3))
  }

  @Test
  fun toStringTest()
  {
    val createCommand = CreateCommand(listOf("2", "2"))
    val canvas = createCommand.execute()

    assertEquals("""
      ----
      |  |
      |  |
      ----
    """.trimIndent(), canvas.toString())
  }
}