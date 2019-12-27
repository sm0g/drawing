package com.example.drawing.command

import com.example.drawing.domain.Canvas.Companion.EMPTY_SYMBOL
import com.example.drawing.domain.Canvas.Companion.HORIZONTAL_SYMBOL
import com.example.drawing.domain.Canvas.Companion.VERTICAL_SYMBOL
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CreateCommandTest {
  @Test
  fun executeTest() {
    val createCommand = CreateCommand(listOf("2", "2"))
    val blankCanvas = createCommand.execute()

    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(0, 0))
    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(1, 0))
    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(2, 0))
    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(3, 0))

    assertEquals(VERTICAL_SYMBOL, blankCanvas.getNode(0, 1))
    assertEquals(EMPTY_SYMBOL, blankCanvas.getNode(1, 1))
    assertEquals(EMPTY_SYMBOL, blankCanvas.getNode(2, 1))
    assertEquals(VERTICAL_SYMBOL, blankCanvas.getNode(3, 1))

    assertEquals(VERTICAL_SYMBOL, blankCanvas.getNode(0, 2))
    assertEquals(EMPTY_SYMBOL, blankCanvas.getNode(1, 2))
    assertEquals(EMPTY_SYMBOL, blankCanvas.getNode(2, 2))
    assertEquals(VERTICAL_SYMBOL, blankCanvas.getNode(3, 2))

    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(0, 3))
    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(1, 3))
    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(2, 3))
    assertEquals(HORIZONTAL_SYMBOL, blankCanvas.getNode(3, 3))
  }
}