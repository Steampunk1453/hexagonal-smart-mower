package com.smart.mower.domain.plateau

import com.smart.mower.domain.mower.Mower

class Plateau(val plateauId: PlateauId, val width: Int, val height: Int) {
    internal val mowers = mutableListOf<Mower>()

    init {
        if (width < 0) throw PlateauException("Width grid size should be positive")
        if (height < 0) throw PlateauException("Height grid size should be positive")
    }

    fun addMower(mower: Mower) {
        mowers.add(mower)
    }

}
