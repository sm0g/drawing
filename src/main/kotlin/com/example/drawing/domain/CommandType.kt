package com.example.drawing.domain

enum class CommandType(private val value: String) {
  ERASE("C"),
  LINE("L"),
  RECTANGLE("R"),
  BUCKET_FILL("B"),
  QUIT("Q");

  override fun toString() = value

  companion object {
    fun parse(commandInput: String): CommandType {
      val commandType = values().firstOrNull { it.value == commandInput }
      require(commandType != null) { "Unknown command: $commandType" }

      return commandType
    }
  }
}