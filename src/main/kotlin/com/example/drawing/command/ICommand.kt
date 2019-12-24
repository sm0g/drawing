package com.example.drawing.command

import com.example.drawing.domain.Canvas

interface ICommand {
  fun execute(): Canvas
}