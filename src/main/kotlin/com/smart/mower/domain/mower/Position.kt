package com.smart.mower.domain.mower

class Position(var x: Int, var y: Int) {

    init {
        if (x < 0) throw MowerException("X position should be positive")
        if (y < 0) throw MowerException("Y position should be positive")
    }

}
