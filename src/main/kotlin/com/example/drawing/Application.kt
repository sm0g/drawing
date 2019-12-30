package com.example.drawing

import com.example.drawing.domain.Canvas
import com.example.drawing.domain.CommandInfo
import com.example.drawing.domain.CommandType

val intro = """
  Supported commands:
  C w h             Should create a new canvas of width w and height h.
  L x1 y1 x2 y2     Should create a new line from (x1,y1) to (x2,y2) . Currently only horizontal or vertical lines are supported. 
                    Horizontal and vertical lines will be drawn using the x character.
  R x1 y1 x2 y2     Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). 
                    Horizontal and vertical lines will be drawn using the x character.
  B x y c           Should fill the entire area connected to (x,y) with colour 'c'. 
                    The behaviour of this is the same as that of the "bucket fill" tool in paint programs.
  Q                 Should quit the program.  
""".trimIndent()

fun main(args: Array<String>) {
  var canvas: Canvas? = null
  val commandCreator = CommandCreator()

  println(intro)

  while (true) {
    println()
    println("Please enter command:")

    val input = readLine()

    if (input == null) {
      println("System.in has failed")
      break
    }

    try {
      val commandInfo = CommandInfo(input)

      if(commandInfo.commandType == CommandType.CREATE_CANVAS) {
        canvas = Canvas(commandInfo.commandArgs)
      }
      else if(canvas == null) {
        print("Create canvas at first")
        continue
      }

      val command = commandCreator.createCommand(commandInfo, canvas)
      canvas = command.execute()

      println(canvas.toString())
    }
    catch (ex: IllegalArgumentException) {
      println(ex.message)
    }
    catch (ex: Exception) {
      println(ex.toString())
    }
  }
}