package com.example.drawing.command

import com.example.drawing.domain.Canvas
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows

class BucketFillCommandTest {
  @Test
  fun emptyArgsTest() {
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf(), Canvas(2, 2))
    }
  }

  @Test
  fun invalidArgsSizeTest() {
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("5"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidColorArgTest() {
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "1", "qwe"), Canvas(2, 2))
    }
  }

  @Test
  fun invalidCoordsTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("0", "1", "z"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "6", "z"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("ss", "2", "z"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "3.4", "z"), canvas)
    }
  }

  @Test
  fun outOfCanvasTest() {
    val canvas = Canvas(2, 2)

    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("1", "1", "1", "3"), canvas)
    }
    assertThrows(IllegalArgumentException::class.java) {
      BucketFillCommand(listOf("0", "1", "0", "2"), canvas)
    }
  }

  @Test
  fun toStringTest()
  {
    val canvas = Canvas(5, 3)
    val bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    bucketFillCommand.execute()

    assertEquals(buildString {
      appendln("       ")
      appendln(" zzzzz ")
      appendln(" zzzzz ")
      appendln(" zzzzz ")
      append("       ")
    }, canvas.toString())
  }

  @Test
  fun overwriteColorTest() {
    val canvas = Canvas(5, 3)
    var bucketFillCommand = BucketFillCommand(listOf("1", "1", "z"), canvas)
    bucketFillCommand.execute()

    assertEquals(buildString {
      appendln("       ")
      appendln(" zzzzz ")
      appendln(" zzzzz ")
      appendln(" zzzzz ")
      append("       ")
    }, canvas.toString())

    bucketFillCommand = BucketFillCommand(listOf("1", "1", "o"), canvas)
    bucketFillCommand.execute()

    assertEquals(buildString {
      appendln("       ")
      appendln(" ooooo ")
      appendln(" ooooo ")
      appendln(" ooooo ")
      append("       ")
    }, canvas.toString())
  }
}