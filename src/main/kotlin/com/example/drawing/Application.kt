package com.example.drawing

import com.example.drawing.domain.Canvas

fun main(args: Array<String>) {
  var canvas: Canvas? = null
  val commandCreator = CommandCreator()

  while (true) {
    println("Please enter command:")

    val input = readLine()

    if (input == null) {
      println("System.in has failed")
      break
    }

    try {
      val commandArgs = input.split(' ')
      val command = commandCreator.createCommand(commandArgs, canvas)
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