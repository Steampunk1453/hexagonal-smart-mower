package com.smart.mower.domain.plateau

class PlateauSize(val width: Int, val height: Int) {

    init {
        if (width < 0) throw PlateauException("Width  should be positive")
        if (height < 0) throw PlateauException("Height should be positive")
    }

}
