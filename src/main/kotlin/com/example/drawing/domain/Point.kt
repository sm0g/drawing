package com.example.drawing.domain

import com.example.drawing.utils.toPositiveIntParam

class Point(val x: Int, val y: Int) {
  constructor(xRaw: String, yRaw: String)
    : this(xRaw.toPositiveIntParam(), yRaw.toPositiveIntParam())
}