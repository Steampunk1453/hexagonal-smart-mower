package com.smart.mower.domain.plateau

class Plateau(val plateauId: PlateauId, val width: Int, val height: Int) {

    init {
        if (width < 0) throw PlateauException("Width grid size should be positive")
        if (height < 0) throw PlateauException("Height grid size should be positive")
    }

}
