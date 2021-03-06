package com.example.drawing.domain

enum class CommandType(private val value: String) {
  CREATE("C"),
  LINE("L"),
  RECTANGLE("R"),
  BUCKET_FILL("B"),
  QUIT("Q");

  override fun toString() = value

  companion object {
    fun parse(commandInput: String): CommandType {
      val commandType = values().firstOrNull {
        it.value == commandInput.toUpperCase()
      }
      require(commandType != null) { "Unknown command: $commandInput" }

      return commandType
    }
  }
}