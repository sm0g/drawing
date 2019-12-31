package com.example.drawing.command

import com.example.drawing.domain.Canvas
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BorderCommandTest {
  @Test
  fun toStringTest()
  {
    val canvas = Canvas(2, 2)
    val createCommand = BorderCommand(canvas)
    createCommand.execute()

    assertEquals("""
      ----
      |  |
      |  |
      ----
    """.trimIndent(), canvas.toString())
  }
}