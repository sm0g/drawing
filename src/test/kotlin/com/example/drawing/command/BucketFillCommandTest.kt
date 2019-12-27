package com.example.drawing.command

import com.example.drawing.domain.Canvas
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BucketFillCommandTest {
  @Test
  fun toStringTest()
  {
    var canvas = Canvas(5, 3)
    val rectangleCommand = RectangleCommand(listOf("2", "1", "4", "3"), canvas)
    canvas = rectangleCommand.execute()
    val bucketFillCommand = BucketFillCommand(listOf("3", "2", "z"), canvas)
    canvas = bucketFillCommand.execute()
    
    val expected = StringBuilder()
      .appendln("-------")
      .appendln("| xxx |")
      .appendln("| xzx |")
      .appendln("| xxx |")
        .append("-------")

    Assertions.assertEquals(expected.toString(), canvas.toString())
  }

  @Test
  fun toStringTest1()
  {
    var canvas = Canvas(5, 3)
    val rectangleCommand = RectangleCommand(listOf("2", "1", "4", "3"), canvas)
    canvas = rectangleCommand.execute()
    val bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    canvas = bucketFillCommand.execute()

    val expected = StringBuilder()
      .appendln("-------")
      .appendln("|zxxx |")
      .appendln("|zx x |")
      .appendln("|zxxx |")
        .append("-------")

    Assertions.assertEquals(expected.toString(), canvas.toString())
  }
}