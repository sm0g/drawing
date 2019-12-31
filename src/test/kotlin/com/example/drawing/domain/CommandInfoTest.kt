package com.example.drawing.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertLinesMatch
import org.junit.jupiter.api.Test

class CommandInfoTest {
  @Test
  fun validCommandTest() {
    var commandInfo = CommandInfo("C 20 4")
    assertEquals(CommandType.CREATE, commandInfo.commandType)
    assertLinesMatch(listOf("20", "4"), commandInfo.commandArgs)

    commandInfo = CommandInfo("B 10 3 o")
    assertEquals(CommandType.BUCKET_FILL, commandInfo.commandType)
    assertLinesMatch(listOf("10", "3", "o"), commandInfo.commandArgs)
  }

  @Test
  fun invalidCommandTest() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      CommandInfo("BB")
    }
  }
}