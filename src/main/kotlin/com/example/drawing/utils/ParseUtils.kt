package com.example.drawing.utils

fun String.toPositiveIntParam(): Int {
  val parsedValue = this.toIntOrNull()

  if(parsedValue == null || parsedValue < 0)
    throw IllegalArgumentException("Argument must be a positive integer")

  return parsedValue
}