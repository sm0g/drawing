package com.example.drawing.command

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CreateCommandTest {

  @Test
  fun executeTest() {
    val createCommand = CreateCommand(listOf("3", "3"))
    val blankCanvas = createCommand.execute()
    assertEquals(blankCanvas.toString(), """
      -----
      |   |
      |   |
      |   |
      -----
    """.trimIndent())
  }
}